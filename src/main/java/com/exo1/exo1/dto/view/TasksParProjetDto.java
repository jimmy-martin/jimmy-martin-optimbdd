package com.exo1.exo1.dto.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TasksParProjetDto {
    private Long projetId;
    private Long nbTasks;
}
