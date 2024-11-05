package com.exo1.exo1.mapper.view;

import com.exo1.exo1.dto.view.TasksParProjetDto;
import com.exo1.exo1.entity.view.TasksParProjet;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TasksParProjetMapper {
    TasksParProjetDto toDto(TasksParProjet tasksParProjet);
    TasksParProjet toEntity(TasksParProjetDto tasksParProjetDto);
    List<TasksParProjetDto> toDtos(List<TasksParProjet> tasksParProjets);
    List<TasksParProjet> toEntities(List<TasksParProjetDto> tasksParProjetDtos);
}
