package com.exo1.exo1.mapper.view;

import com.exo1.exo1.dto.view.TasksParProjetDto;
import com.exo1.exo1.entity.view.TasksParProjet;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-05T15:39:01+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
public class TasksParProjetMapperImpl implements TasksParProjetMapper {

    @Override
    public TasksParProjetDto toDto(TasksParProjet tasksParProjet) {
        if ( tasksParProjet == null ) {
            return null;
        }

        TasksParProjetDto tasksParProjetDto = new TasksParProjetDto();

        tasksParProjetDto.setProjetId( tasksParProjet.getProjetId() );
        tasksParProjetDto.setNbTasks( tasksParProjet.getNbTasks() );

        return tasksParProjetDto;
    }

    @Override
    public TasksParProjet toEntity(TasksParProjetDto tasksParProjetDto) {
        if ( tasksParProjetDto == null ) {
            return null;
        }

        TasksParProjet tasksParProjet = new TasksParProjet();

        tasksParProjet.setProjetId( tasksParProjetDto.getProjetId() );
        tasksParProjet.setNbTasks( tasksParProjetDto.getNbTasks() );

        return tasksParProjet;
    }

    @Override
    public List<TasksParProjetDto> toDtos(List<TasksParProjet> tasksParProjets) {
        if ( tasksParProjets == null ) {
            return null;
        }

        List<TasksParProjetDto> list = new ArrayList<TasksParProjetDto>( tasksParProjets.size() );
        for ( TasksParProjet tasksParProjet : tasksParProjets ) {
            list.add( toDto( tasksParProjet ) );
        }

        return list;
    }

    @Override
    public List<TasksParProjet> toEntities(List<TasksParProjetDto> tasksParProjetDtos) {
        if ( tasksParProjetDtos == null ) {
            return null;
        }

        List<TasksParProjet> list = new ArrayList<TasksParProjet>( tasksParProjetDtos.size() );
        for ( TasksParProjetDto tasksParProjetDto : tasksParProjetDtos ) {
            list.add( toEntity( tasksParProjetDto ) );
        }

        return list;
    }
}
