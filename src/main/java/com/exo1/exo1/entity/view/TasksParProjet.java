package com.exo1.exo1.entity.view;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tasks_per_project")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TasksParProjet {
    @Id
    @Column(name = "projet_id")
    private Long projetId;

    @Column(name = "tasks_count")
    private Long nbTasks;
}
