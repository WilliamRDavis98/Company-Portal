package com.cooksys.groupfinal.services.impl;

import com.cooksys.groupfinal.dtos.ProjectDto;
import com.cooksys.groupfinal.entities.Project;
import com.cooksys.groupfinal.exceptions.NotFoundException;
import com.cooksys.groupfinal.mappers.ProjectMapper;
import com.cooksys.groupfinal.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import com.cooksys.groupfinal.services.ProjectService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    @Override
    public ProjectDto findProjectById(Long id) {
        Project projectToFind = projectRepository.findById(id).get();
        if (projectToFind == null) {
            throw new NotFoundException("This project doesn't exist");
            // I'm not sure if we will need to throw this error if this method is purely for rendering
            // so can remove it if need be, depending on how the front end team goes about their error handling
        }

        return projectMapper.entityToDto(projectToFind);
    }
}
