package com.cooksys.groupfinal.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.cooksys.groupfinal.dtos.CredentialsDto;
import com.cooksys.groupfinal.dtos.FullUserDto;
import com.cooksys.groupfinal.dtos.UserRequestDto;
import com.cooksys.groupfinal.entities.Credentials;
import com.cooksys.groupfinal.entities.User;
import com.cooksys.groupfinal.exceptions.BadRequestException;
import com.cooksys.groupfinal.exceptions.NotAuthorizedException;
import com.cooksys.groupfinal.exceptions.NotFoundException;
import com.cooksys.groupfinal.mappers.CredentialsMapper;
import com.cooksys.groupfinal.mappers.FullUserMapper;
import com.cooksys.groupfinal.mappers.UserMapper;
import com.cooksys.groupfinal.repositories.UserRepository;
import com.cooksys.groupfinal.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final FullUserMapper fullUserMapper;
	private final CredentialsMapper credentialsMapper;

	private final UserMapper userMapper;

	private User findUser(String username) {
		Optional<User> user = userRepository.findByCredentialsUsernameAndActiveTrue(username);
		if (user.isEmpty()) {
			throw new NotFoundException("The username provided does not belong to an active user.");
		}
		return user.get();
	}

	@Override
	public FullUserDto login(CredentialsDto credentialsDto) {
		if (credentialsDto == null || credentialsDto.getUsername() == null || credentialsDto.getPassword() == null) {
			throw new BadRequestException("A username and password are required.");
		}
		Credentials credentialsToValidate = credentialsMapper.dtoToEntity(credentialsDto);
		User userToValidate = findUser(credentialsDto.getUsername());
		if (!userToValidate.getCredentials().equals(credentialsToValidate)) {
			throw new NotAuthorizedException("The provided credentials are invalid.");
		}
		if (userToValidate.getStatus().equals("PENDING")) {
			userToValidate.setStatus("JOINED");
			userRepository.saveAndFlush(userToValidate);
		}
		return fullUserMapper.entityToFullUserDto(userToValidate);
	}

//    @Override
//    public List<UserResponseDto> getAllUsers() {
//        List<User> users = userRepository.findAll();
//        Set<User> uniqueUsers = new HashSet<>(users);
//        List<User> noDuplicateUsers = new ArrayList<>(uniqueUsers);
//        return userMapper.entitiesToDtos(noDuplicateUsers);
//
//    }

	@Override
	public List<FullUserDto> getAllUsers() {
		List<User> users = userRepository.findAll();
		Set<User> uniqueUsers = new HashSet<>(users);
		List<User> noDuplicateUsers = new ArrayList<>(uniqueUsers);
		return userMapper.entitiesToDtos(noDuplicateUsers);

	}

	@Override
	public FullUserDto createUser(UserRequestDto userRequestDto) {
		// request validation
		// make sure first name, last name, and email are given
		if (userRequestDto.getProfile().getFirstName() == null || userRequestDto.getProfile().getLastName() == null
				|| userRequestDto.getProfile().getEmail() == null) {
			throw new BadRequestException("First name, last name, and email are required.");
		}
		// make sure a password is passed
		if (userRequestDto.getCredentials().getUsername() == null
				|| userRequestDto.getCredentials().getPassword() == null) {
			throw new BadRequestException("A username and password is required to create a new user.");
		}

		// check if username is already taken
		// theoretically should be taken care of in the frontend, so may be redundant
		// code

		Optional<User> optionalUser = userRepository
				.findByCredentialsUsernameAndActiveTrue(userRequestDto.getCredentials().getUsername());
		if (optionalUser.isPresent() && optionalUser.get().isActive()) {
			throw new BadRequestException("Username is already taken.");
		}

		// check for inactive usernames??
		// how to handle usernames that are taken by people who are not active

		// convert dto to entity
		User userToSave = fullUserMapper.requestDtoToEntity(userRequestDto);
		userToSave.setActive(true);
		// do i need to set company?

		// save and flush with userRepository
		userRepository.saveAndFlush(userToSave);
		// convert it to a responseDto from entity and return that
		return fullUserMapper.entityToFullUserDto(userToSave);
	}

}
