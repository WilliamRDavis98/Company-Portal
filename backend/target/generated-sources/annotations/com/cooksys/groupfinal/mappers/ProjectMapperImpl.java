package com.cooksys.groupfinal.mappers;

import com.cooksys.groupfinal.dtos.ProjectDto;
import com.cooksys.groupfinal.dtos.ProjectRequestDto;
import com.cooksys.groupfinal.entities.Project;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-27T13:08:40-0500",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 17.0.8.1 (Eclipse Adoptium)"
)
@Component
public class ProjectMapperImpl implements ProjectMapper {

    @Autowired
    private TeamMapper teamMapper;

    @Override
    public ProjectDto entityToDto(Project project) {
        if ( project == null ) {
            return null;
        }

        ProjectDto projectDto = new ProjectDto();

        projectDto.setId( project.getId() );
        projectDto.setName( project.getName() );
        projectDto.setDescription( project.getDescription() );
        projectDto.setActive( project.isActive() );
        projectDto.setTeam( teamMapper.entityToDto( project.getTeam() ) );

        return projectDto;
    }

    @Override
    public List<ProjectDto> entitiesToDtos(List<Project> filteredProjects) {
        if ( filteredProjects == null ) {
            return null;
        }

        List<ProjectDto> list = new ArrayList<ProjectDto>( filteredProjects.size() );
        for ( Project project : filteredProjects ) {
            list.add( entityToDto( project ) );
        }

        return list;
    }

    @Override
    public Project requestDtoToEntity(ProjectRequestDto projectRequestDto) {
        if ( projectRequestDto == null ) {
            return null;
        }

        Project project = new Project();

        project.setName( projectRequestDto.getName() );
        project.setDescription( projectRequestDto.getDescription() );

        return project;
    }
}
