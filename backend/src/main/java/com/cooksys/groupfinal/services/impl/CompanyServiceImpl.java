package com.cooksys.groupfinal.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


import com.cooksys.groupfinal.exceptions.BadRequestException;
import com.cooksys.groupfinal.exceptions.NotAuthorizedException;
import com.cooksys.groupfinal.exceptions.NotFoundException;
import com.cooksys.groupfinal.repositories.UserRepository;
import org.springframework.stereotype.Service;




import com.cooksys.groupfinal.entities.Announcement;
import com.cooksys.groupfinal.entities.Company;
import com.cooksys.groupfinal.entities.Project;
import com.cooksys.groupfinal.entities.Team;
import com.cooksys.groupfinal.entities.User;


import com.cooksys.groupfinal.dtos.AnnouncementDto;
import com.cooksys.groupfinal.dtos.FullUserDto;
import com.cooksys.groupfinal.dtos.ProjectDto;
import com.cooksys.groupfinal.dtos.TeamDto;

import com.cooksys.groupfinal.mappers.AnnouncementMapper;
import com.cooksys.groupfinal.mappers.FullUserMapper;
import com.cooksys.groupfinal.mappers.ProjectMapper;
import com.cooksys.groupfinal.mappers.TeamMapper;


import com.cooksys.groupfinal.repositories.CompanyRepository;
import com.cooksys.groupfinal.repositories.TeamRepository;
import com.cooksys.groupfinal.services.CompanyService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

	private final CompanyRepository companyRepository;
	private final TeamRepository teamRepository;
  private final UserRepository userRepository;
	private final FullUserMapper fullUserMapper;
	private final BasicUserMapper basicUserMapper;
	private final AnnouncementMapper announcementMapper;
	private final TeamMapper teamMapper;
	private final ProjectMapper projectMapper;
	private final ProfileMapper profileMapper;
	private final CompanyMapper companyMapper;


	private Company findCompany(Long id) {
		Optional<Company> company = companyRepository.findById(id);
		if (company.isEmpty()) {
			throw new NotFoundException("A company with the provided id does not exist.");
		}
		return company.get();
	}

	private Team findTeam(Long id) {
        Optional<Team> team = teamRepository.findById(id);
        if (team.isEmpty()) {
            throw new NotFoundException("A team with the provided id does not exist.");
        }
        return team.get();
    }
  
  private ProfileDto getProfileDtoForUser(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new NotFoundException("User with id " + userId + " not found"));

		return profileMapper.entityToDto(user.getProfile());
	}  

	@Override
	public List<CompanyDto> getAllCompanies() {
		return companyMapper.entitiesToDtos(companyRepository.findAll());
	}

	@Override
	public List<FullUserDto> getAllUsers(Long id) {
		Company company = findCompany(id);
		List<User> filteredUsers = new ArrayList<>();
		company.getEmployees().forEach(filteredUsers::add);
		filteredUsers.removeIf(user -> !user.isActive());
		return fullUserMapper.entitiesToFullUserDtos(filteredUsers);
	}

	@Override
	public List<AnnouncementDto> getAllAnnouncements(Long id) {
		Company company = findCompany(id);
		List<Announcement> sortedList = new ArrayList<Announcement>(company.getAnnouncements());
		sortedList.sort(Comparator.comparing(Announcement::getDate).reversed());
		List<Announcement> sortedList2 = new ArrayList<Announcement>(sortedList);
		return announcementMapper.entitiesToDtos(sortedList2);
	}

	@Override
	public List<TeamDto> getAllTeams(Long id) {
		Company company = findCompany(id);
		return teamMapper.entitiesToDtos(company.getTeams());
	}

	@Override
	public List<ProjectDto> getAllProjects(Long companyId, Long teamId) {
		Company company = findCompany(companyId);
		Team team = findTeam(teamId);
		if (!company.getTeams().contains(team)) {
			throw new NotFoundException("A team with id " + teamId + " does not exist at company with id " + companyId + ".");
		}
		List<Project> filteredProjects = new ArrayList<>();
		team.getProjects().forEach(filteredProjects::add);
		filteredProjects.removeIf(project -> !project.isActive());
		return projectMapper.entitiesToDtos(filteredProjects);
	}



	@Override
	public TeamDto addTeamToCompany(Long id, TeamDto teamDto) {
		if (teamDto == null || teamDto.getName() == null || teamDto.getDescription() == null) {
			throw new BadRequestException("Invalid information given.");
		}
		Company company = companyRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Company with id " + id + " was not found."));
		Team newTeam = new Team();
		newTeam.setName(teamDto.getName());
		newTeam.setDescription(teamDto.getDescription());
		if (teamDto.getTeammates() != null && !teamDto.getTeammates().isEmpty()) {
			Set<User> teammates = basicUserMapper.basicDtosToEntities(teamDto.getTeammates());
			for (User teammate : teammates) {
				ProfileDto profileDto = getProfileDtoForUser(teammate.getId());
				if (profileDto != null) {
					Profile profile = profileMapper.dtoToEntity(profileDto);
					teammate.setProfile(profile);
				}
			}
			newTeam.setTeammates(teammates);
		} else {
			newTeam.setTeammates(new HashSet<>());
		}
		Team savedTeam = teamRepository.saveAndFlush(newTeam);
		company.getTeams().add(savedTeam);
		companyRepository.saveAndFlush(company);
		return teamMapper.entityToDto(savedTeam);
	}

	@Override
	public List<FullUserDto> getAllTeamUsers(Long companyId, Long teamId) {
		Company company = findCompany(companyId);
		Team team = findTeam(teamId);
		if (!company.getTeams().contains(team)) {
			throw new NotFoundException("A team with id " + teamId + " does not exist at company with id " + companyId +".");
		}
		List<User> filteredUsers = new ArrayList<>();
		team.getTeammates().forEach(filteredUsers::add);
		filteredUsers.removeIf(user -> !user.isActive());
		return fullUserMapper.entitiesToFullUserDtos(filteredUsers);
	}
}




