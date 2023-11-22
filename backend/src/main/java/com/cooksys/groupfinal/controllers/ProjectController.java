package com.cooksys.groupfinal.controllers;


import com.cooksys.groupfinal.dtos.ProjectDto;
import org.springframework.web.bind.annotation.*;

import com.cooksys.groupfinal.dtos.ProjectDto;
import com.cooksys.groupfinal.dtos.ProjectRequestDto;
import com.cooksys.groupfinal.services.ProjectService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {
	
	
	 private ProjectService projectService;

	@GetMapping("/{id}")
	public ProjectDto getProjectById(@PathVariable Long id) {
		return projectService.findProjectById(id);
	}


	@PatchMapping("/{id}")
	public ProjectDto updateProject(@PathVariable Long id, @RequestBody ProjectDto projectDto) {

	}

}
