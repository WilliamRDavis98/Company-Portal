package com.cooksys.groupfinal.services;

import com.cooksys.groupfinal.dtos.CredentialsDto;
import com.cooksys.groupfinal.dtos.FullUserDto;
import com.cooksys.groupfinal.dtos.UserResponseDto;

import java.util.List;

public interface UserService {

	FullUserDto login(CredentialsDto credentialsDto);

	List<UserResponseDto> getAllUsers();
}

