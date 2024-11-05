package com.exo1.exo1.controller;

import com.exo1.exo1.dto.ProjetDto;
import com.exo1.exo1.dto.UserDto;
import com.exo1.exo1.dto.view.TasksParProjetDto;
import com.exo1.exo1.service.ProjetService;
import com.exo1.exo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projets")
public class ProjetController {
    @Autowired
    private ProjetService projetService;

    @GetMapping
    public ResponseEntity<List<ProjetDto>> findAll(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    )
    {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        return ResponseEntity.ok(projetService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetDto> findById(@PathVariable Long id)
    {
        return ResponseEntity.ok(projetService.findById(id));
    }

    @GetMapping("/stats")
    public ResponseEntity<List<TasksParProjetDto>> getStats()
    {
        return ResponseEntity.ok(projetService.getStats());
    }

    @GetMapping("/{id}/stats")
    public ResponseEntity<TasksParProjetDto> getStatsById(@PathVariable Long id)
    {
        return ResponseEntity.ok(projetService.getStatsById(id));
    }

    @PostMapping
    public ResponseEntity<ProjetDto> save(@RequestBody ProjetDto projetDto) {
        return ResponseEntity.ok(projetService.save(projetDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjetDto> update(@PathVariable Long id, @RequestBody ProjetDto projetDto) {
        return ResponseEntity.ok(projetService.update(id, projetDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        projetService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
