package com.exo1.exo1.service;

import com.exo1.exo1.dto.ProjetDto;
import com.exo1.exo1.dto.view.TasksParProjetDto;
import com.exo1.exo1.entity.Projet;
import com.exo1.exo1.mapper.ProjetMapper;
import com.exo1.exo1.mapper.view.TasksParProjetMapper;
import com.exo1.exo1.repository.ProjetRepository;
import com.exo1.exo1.repository.TaskRepository;
import com.exo1.exo1.repository.view.TasksParProjetRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjetService {
    private final JdbcTemplate jdbcTemplate;
    private ProjetRepository projetRepository;
    private ProjetMapper projetMapper;
    private TaskRepository taskRepository;
    private TasksParProjetRepository tasksParProjetRepository;
    private TasksParProjetMapper tasksParProjetMapper;

    @Cacheable(value = "projects_list")
    public List<ProjetDto> findAll(Pageable pageable) {
        return projetMapper.toDtos(projetRepository.findAllWithTasks(pageable).getContent());
    }

    @Cacheable(value = "projects", key = "#id")
    public ProjetDto findById(long id) {
        return projetMapper.toDto(projetRepository.findByIdWithTasks(id).orElse(null));
    }

    @Cacheable(value = "projects_stats_list")
    public List<TasksParProjetDto> getStats() {
        return tasksParProjetMapper.toDtos(tasksParProjetRepository.findAll());
    }

    @Cacheable(value = "projects_stats", key = "#id")
    public TasksParProjetDto getStatsById(Long id) {
        return tasksParProjetMapper.toDto(tasksParProjetRepository.findById(id).orElse(null));
    }

    @CacheEvict(value = "projects_list", allEntries = true)
    public ProjetDto save(ProjetDto projetDto) {
        Projet projet = projetMapper.toEntity(projetDto);
        projet.getTasks().stream().forEach(t -> t.setProjet(projet));
        return projetMapper.toDto(projetRepository.save(projet));
    }

    @Caching(
            put = {
                    @CachePut(value = "projects", key = "#id")
            },
            evict = {
                    @CacheEvict(value = "projects_list", allEntries = true)
            }
    )
    public ProjetDto update(Long id, ProjetDto projetDto) {
        Projet existingProjet = projetRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Projet not found with id " + id));
        projetDto.setId(existingProjet.getId());
        Projet projetUpdated = projetMapper.toEntity(projetDto);
        projetUpdated.getTasks().stream().forEach(t -> {
            if(taskRepository.existsById(t.getId())) {
                t.setProjet(projetUpdated);
            }
        });
        Projet projet = projetRepository.save(projetUpdated);
        refreshMaterializedView();
        return projetMapper.toDto(projet);
    }

    @Caching(
            evict = {
                    @CacheEvict(value = "projects", key = "#id"),
                    @CacheEvict(value = "projects_list", allEntries = true)
            }
    )
    public void delete(Long id) {
        projetRepository.deleteById(id);
        refreshMaterializedView();
    }

    public void refreshMaterializedView() {
        jdbcTemplate.execute("REFRESH MATERIALIZED VIEW tasks_per_project");
    }
}
