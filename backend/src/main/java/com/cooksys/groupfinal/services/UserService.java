package com.cooksys.groupfinal.services;

import java.util.List;

import com.cooksys.groupfinal.dtos.CredentialsDto;
import com.cooksys.groupfinal.dtos.FullUserDto;
import com.cooksys.groupfinal.dtos.UserRequestDto;

public interface UserService {

	FullUserDto login(CredentialsDto credentialsDto);

//	List<UserResponseDto> getAllUsers();

	List<FullUserDto> getAllUsers();

	FullUserDto createUser(UserRequestDto userRequestDto);
}

