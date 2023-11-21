package com.cooksys.groupfinal.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksys.groupfinal.dtos.ProjectDto;
import com.cooksys.groupfinal.dtos.ProjectRequestDto;
import com.cooksys.groupfinal.entities.Project;
import com.cooksys.groupfinal.entities.Team;
import com.cooksys.groupfinal.exceptions.BadRequestException;
import com.cooksys.groupfinal.exceptions.NotFoundException;
import com.cooksys.groupfinal.mappers.ProjectMapper;
import com.cooksys.groupfinal.repositories.ProjectRepository;
import com.cooksys.groupfinal.repositories.TeamRepository;
import com.cooksys.groupfinal.services.TeamService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
	
	private final TeamRepository teamRepository;
	private final ProjectRepository projectRepository;
	private final ProjectMapper projectMapper;

	@Override
	public ProjectDto createProject(Long id, ProjectRequestDto projectRequestDto) {
		if (projectRequestDto.getName() == null || projectRequestDto.getDescription() == null) {
			throw new BadRequestException("Missing project name or description"); 
		}
		if (teamRepository.findById(id) == null) {
			throw new NotFoundException("No team found with id " + id);
		}
		Optional<Project> optionalProject = projectRepository.findByName(projectRequestDto.getName()); 
		Team team = teamRepository.findById(id).orElse(null);
		//Team team = teamRepository.findByName(teamRepository.findById(id).ifPresent(teamRepo -> teamRepo.getName()));
		if (optionalProject.isEmpty()) {
			Project project = projectMapper.requestDtoToEntity(projectRequestDto);
			project.setActive(true);
			project.setTeam(team);
			List<Project> teamProjects = team.getProjects();
			teamProjects.add(project);
			team.setProjects(teamProjects);
			teamRepository.saveAndFlush(team);
			return projectMapper.entityToDto(project); 
		}
		throw new BadRequestException("Project already exists");
	}

}
