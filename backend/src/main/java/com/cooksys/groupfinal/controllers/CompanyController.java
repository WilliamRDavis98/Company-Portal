package com.cooksys.groupfinal.controllers;

import java.util.List;
import java.util.Set;

import com.cooksys.groupfinal.dtos.*;

import com.cooksys.groupfinal.services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {
	
	private final CompanyService companyService;
	
	@GetMapping("/{id}/users")
    public List<FullUserDto> getAllUsers(@PathVariable Long id) {
        return companyService.getAllUsers(id);
    }
	
	@GetMapping("/{id}/announcements")
    public List<AnnouncementDto> getAllAnnouncements(@PathVariable Long id) {
        return companyService.getAllAnnouncements(id);
    }
	
	@GetMapping("/{id}/teams")
    public List<TeamDto> getAllTeams(@PathVariable Long id) {
        return companyService.getAllTeams(id);
    }
	
	@GetMapping("/{companyId}/teams/{teamId}/projects") 
	public List<ProjectDto> getAllProjects(@PathVariable Long companyId, @PathVariable Long teamId) {
		return companyService.getAllProjects(companyId, teamId);
	}
	
	@GetMapping("/{companyId}/teams/{teamId}/users")
	public List<FullUserDto> getAllTeamUsers(@PathVariable Long companyId, @PathVariable Long teamId){
		return companyService.getAllTeamUsers(companyId, teamId);
	}

    @GetMapping
    public List<CompanyDto> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @PostMapping("/{companyId}/teams")
    public TeamDto addTeamToCompany(@PathVariable Long companyId, @RequestBody TeamDto teamDto) {
        return companyService.addTeamToCompany(companyId, teamDto);
    }

}
