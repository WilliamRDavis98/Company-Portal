package com.cooksys.groupfinal.services;

import java.util.List;
import java.util.Set;

import com.cooksys.groupfinal.dtos.*;

public interface CompanyService {

	List<FullUserDto> getAllUsers(Long id);

	List<AnnouncementDto> getAllAnnouncements(Long id);

	List<TeamDto> getAllTeams(Long id);

	List<ProjectDto> getAllProjects(Long companyId, Long teamId);

  TeamDto addTeamToCompany(Long id, TeamDto teamDto);

	List<FullUserDto> getAllTeamUsers(Long companyId, Long teamId);

  List<CompanyDto> getAllCompanies();

}
