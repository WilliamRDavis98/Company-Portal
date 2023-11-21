package com.cooksys.groupfinal.mappers;


import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;

import com.cooksys.groupfinal.dtos.TeamDto;
import com.cooksys.groupfinal.entities.Team;

@Mapper(componentModel = "spring", uses = { BasicUserMapper.class })
public interface TeamMapper {
	
	TeamDto entityToDto(Team team);

  List<TeamDto> entitiesToDtos(List<Team> teams);

	Set<TeamDto> entitiesToDtos(Set<Team> teams);
}