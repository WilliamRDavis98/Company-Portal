package com.cooksys.groupfinal.services.impl;
import com.cooksys.groupfinal.exceptions.NotFoundException;
import java.util.Optional;
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
}
