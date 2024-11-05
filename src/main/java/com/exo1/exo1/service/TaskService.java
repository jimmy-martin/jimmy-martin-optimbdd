package com.exo1.exo1.service;

import com.exo1.exo1.dto.TaskDto;
import com.exo1.exo1.entity.Task;
import com.exo1.exo1.mapper.TaskMapper;
import com.exo1.exo1.repository.TaskRepository;
import com.exo1.exo1.repository.view.TasksParProjetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private TaskRepository taskRepository;
    private TaskMapper taskMapper;
    private ProjetService projetService;

    public List<TaskDto> findAll() {
        return taskMapper.toDtos(taskRepository.findAll());
    }

    public TaskDto findById(long id) {
        return taskMapper.toDto(taskRepository.findById(id).orElse(null));
    }

    public TaskDto save(TaskDto taskDto) {
        Task task = taskRepository.save(taskMapper.toEntity(taskDto));
        projetService.refreshMaterializedView();
        return taskMapper.toDto(task);
    }

    public TaskDto update(Long id, TaskDto taskDto) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task not found with id " + id));
        taskDto.setId(existingTask.getId());
        Task task = taskRepository.save(taskMapper.toEntity(taskDto));
        projetService.refreshMaterializedView();
        return taskMapper.toDto(task);
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
        projetService.refreshMaterializedView();
    }


}
