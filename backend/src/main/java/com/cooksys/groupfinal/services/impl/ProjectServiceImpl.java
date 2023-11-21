package com.cooksys.groupfinal.services.impl;

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

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
	
	
	  private final ProjectRepository projectRepository; private final
	  ProjectMapper projectMapper;
	  
	  @Override public ProjectDto createProject(ProjectRequestDto projectRequestDto) { 
		  if (projectRequestDto.getName() == null || projectRequestDto.getDescription() == null) {
			  throw new BadRequestException("Missing project name or description"); 
		  }
		  Optional<Project> optionalProject = projectRepository.findByName(projectRequestDto.getName()); 
		  if (optionalProject.isEmpty()) {
			  Project project = projectMapper.requestDtoToEntity(projectRequestDto);
			  project.setActive(true);
			  return projectMapper.entityToDto(project); 
		  }
		  throw new BadRequestException("Project already exists");
	  }
	 

}
