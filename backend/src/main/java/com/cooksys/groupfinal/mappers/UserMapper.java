package com.cooksys.groupfinal.mappers;

import com.cooksys.groupfinal.dtos.UserRequestDto;
import com.cooksys.groupfinal.dtos.UserResponseDto;
import com.cooksys.groupfinal.entities.Team;
import com.cooksys.groupfinal.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel="spring", uses = { ProfileMapper.class, TeamMapper.class})
public interface UserMapper {

    @Mapping(source = "profile", target = "profile")
    @Mapping(target = "teamName", expression = "java(getTeamName(user.getTeams()))")
    UserResponseDto entityToDto(User user);

    List<UserResponseDto> entitiesToDtos(List<User> users);

    default String getTeamName(List<Team> teams) {
        if (teams != null && !teams.isEmpty()) {
            return teams.stream().map(Team::getName).collect(Collectors.joining(", "));
        }
        return "";
    }

}
