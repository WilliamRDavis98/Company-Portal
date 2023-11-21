package com.cooksys.groupfinal.services.impl;

import java.util.*;

import com.cooksys.groupfinal.dtos.UserResponseDto;
import com.cooksys.groupfinal.mappers.UserMapper;
import com.cooksys.groupfinal.mappers.TeamMapper;
import org.springframework.stereotype.Service;

import com.cooksys.groupfinal.dtos.CredentialsDto;
import com.cooksys.groupfinal.dtos.FullUserDto;
import com.cooksys.groupfinal.entities.Credentials;
import com.cooksys.groupfinal.entities.User;
import com.cooksys.groupfinal.exceptions.BadRequestException;
import com.cooksys.groupfinal.exceptions.NotAuthorizedException;
import com.cooksys.groupfinal.exceptions.NotFoundException;
import com.cooksys.groupfinal.mappers.CredentialsMapper;
import com.cooksys.groupfinal.mappers.FullUserMapper;
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

    private final TeamMapper teamMapper;

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
    public List<TeamDto> findAllTeamsByUser(Long id) {
        Optional<User> userToFind = userRepository.findById(id);

        if (userToFind.isEmpty()) {
            throw new NotFoundException("This user Id is invalid");
        }

        //        if (!(userToFind.get().isActive())) {
//            throw new NotFoundException("User is inactive");
//        }
//        commented out for now pending discussions with frontend team

        List<TeamDto> listOfTeamsUserIsOn = teamMapper.entitiesToDtos(userToFind.get().getTeams());

        return listOfTeamsUserIsOn;

    }

    @Override
    public FullUserDto getUserById(Long id) {
        Optional<User> userToFind = userRepository.findById(id);

        if (userToFind.isEmpty()) {
            throw new NotFoundException("This user Id is invalid");
        }

//        if (!(userToFind.get().isActive())) {
//            throw new NotFoundException("User is inactive");
//        }
//        commented out for now pending discussions with frontend team

        FullUserDto fullUserDto = fullUserMapper.entityToFullUserDto(userToFind.get());

        if (fullUserDto.isAdmin()) {
            return fullUserDto;
        }
        // so if the user is not an admin, the idea here is to remove any data that they shouldn't be privy to
        // could modify it so it just returns an error, but this would give flexiblity for making the call
        fullUserDto.setTeams(new ArrayList<>());
        fullUserDto.setCompanies(new ArrayList<>());

        return fullUserDto;
    }


}
