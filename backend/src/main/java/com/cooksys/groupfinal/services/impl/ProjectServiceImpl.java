package com.cooksys.groupfinal.services.impl;
import com.cooksys.groupfinal.exceptions.NotFoundException;
import java.util.Optional;

import com.cooksys.groupfinal.mappers.TeamMapper;
import org.springframework.stereotype.Service;
import com.cooksys.groupfinal.dtos.ProjectDto;
import com.cooksys.groupfinal.dtos.ProjectRequestDto;
import com.cooksys.groupfinal.entities.Project;
import com.cooksys.groupfinal.exceptions.BadRequestException;
import com.cooksys.groupfinal.mappers.ProjectMapper;
import com.cooksys.groupfinal.repositories.ProjectRepository;
import com.cooksys.groupfinal.services.ProjectService;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
	
	

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    private final TeamMapper teamMapper;

    @Override
    public ProjectDto findProjectById(Long id) {
        Optional<Project> projectToFind = projectRepository.findById(id);
        if (projectToFind.isEmpty()) {
            throw new NotFoundException("This project doesn't exist");
            // I'm not sure if we will need to throw this error if this method is purely for rendering
            // so can remove it if need be, depending on how the front end team goes about their error handling
        }
        // might add an isActive error handling as well

        return projectMapper.entityToDto(projectToFind.get());
    }

    @Override
    public ProjectDto updateProject(Long id, ProjectDto projectDto) {
        // I could also get the id from the project Dto if we want to limit the variables passed in
        Optional<Project> projectToFind = projectRepository.findById(id);
        if (projectToFind.isEmpty()) {
            throw new NotFoundException("This project doesn't exist");
        }
        Project projectToEdit = projectToFind.get();
        projectToEdit.setName(projectDto.getName());
        // description, active, lastUpdated, team
        projectToEdit.setDescription(projectDto.getDescription());
        projectToEdit.setLastUpdated(projectDto.getLastUpdated());
        // was thinking of getting lastUpdated from the frontend, but I could also probably set a timestamp with the current time
        // would look like this
        // Date currentDate = new Date();
        // projectToEdit.setLastUpdated(currentDate.getTime());
        projectToEdit.setTeam(teamMapper.dtoToEntity(projectDto.getTeam()));
        // maybe I add a save team functionality as well, or do an edit team, don't think its neccessary tho
        projectRepository.saveAndFlush(projectToEdit);

        return projectDto;
        // could return the actual project, but I'm guessing the dto is better for safeguarding data
        // can verify with a get projects by id call after to see if updated
    }
}
