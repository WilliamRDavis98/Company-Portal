package com.cooksys.groupfinal.mappers;

import java.util.List;
import java.util.Set;

import com.cooksys.groupfinal.dtos.BasicUserDto;
import com.cooksys.groupfinal.dtos.UserRequestDto;
import com.cooksys.groupfinal.entities.User;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { ProfileMapper.class, CredentialsMapper.class })
public interface BasicUserMapper {

    BasicUserDto entityToBasicUserDto(User user);
    
    List<BasicUserDto> entitiesToBasicUserDtos(List<User> users);
    
    User requestDtoToEntity(UserRequestDto userRequestDto);

    List<User> basicDtosToEntities(List<BasicUserDto> teammates);
}
