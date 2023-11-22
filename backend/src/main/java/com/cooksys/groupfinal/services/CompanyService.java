package com.cooksys.groupfinal.services;

import java.util.List;

import com.cooksys.groupfinal.dtos.AnnouncementDto;
import com.cooksys.groupfinal.dtos.BasicUserDto;
import com.cooksys.groupfinal.dtos.CompanyDto;
import com.cooksys.groupfinal.dtos.FullUserDto;
import com.cooksys.groupfinal.dtos.ProjectDto;
import com.cooksys.groupfinal.dtos.TeamDto;

public interface CompanyService {

	List<BasicUserDto> getAllUsers(Long id);

	List<AnnouncementDto> getAllAnnouncements(Long id);

	List<TeamDto> getAllTeams(Long id);

	List<ProjectDto> getAllProjects(Long companyId, Long teamId);

	List<FullUserDto> getAllTeamUsers(Long companyId, Long teamId);

    List<CompanyDto> getAllCompanies();

	TeamDto addTeamToCompany(Long companyId, TeamDto teamDto);
}
