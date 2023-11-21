package com.cooksys.groupfinal.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.groupfinal.dtos.ProjectDto;
import com.cooksys.groupfinal.dtos.ProjectRequestDto;
import com.cooksys.groupfinal.services.ProjectService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {
	
	
	  private final ProjectService projectService;
	  
	  @PostMapping() public ProjectDto createProject(@RequestBody ProjectRequestDto
	  projectRequestDto) { return projectService.createProject(projectRequestDto);
	  }
	 

}
