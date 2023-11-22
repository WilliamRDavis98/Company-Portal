package com.cooksys.groupfinal.services;

import java.util.List;
import java.util.Set;

import com.cooksys.groupfinal.dtos.AnnouncementDto;
import com.cooksys.groupfinal.dtos.FullUserDto;
import com.cooksys.groupfinal.dtos.ProjectDto;
import com.cooksys.groupfinal.dtos.ProjectRequestDto;
import com.cooksys.groupfinal.dtos.TeamDto;
import com.cooksys.groupfinal.dtos.*;

public interface CompanyService {

	List<FullUserDto> getAllUsers(Long id);

	List<AnnouncementDto> getAllAnnouncements(Long id);

	List<TeamDto> getAllTeams(Long id);

	List<ProjectDto> getAllProjects(Long companyId, Long teamId);

	List<FullUserDto> getAllTeamUsers(Long companyId, Long teamId);

    List<CompanyDto> getAllCompanies();

	TeamDto addTeamToCompany(Long companyId, TeamDto teamDto);

	CompanyDto getCompanyById(Long companyId);
}
