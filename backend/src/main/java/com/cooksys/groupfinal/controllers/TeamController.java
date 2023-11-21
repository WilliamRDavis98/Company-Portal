package com.cooksys.groupfinal.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.groupfinal.dtos.ProjectDto;
import com.cooksys.groupfinal.dtos.ProjectRequestDto;
import com.cooksys.groupfinal.services.TeamService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamController {
	
	private final TeamService teamService;
	
	@PostMapping("/{id}/projects") public ProjectDto createProject(@PathVariable Long id, @RequestBody ProjectRequestDto projectRequestDto) {
		return teamService.createProject(id, projectRequestDto);
	}

}
