package com.cooksys.groupfinal.mappers;

import com.cooksys.groupfinal.dtos.BasicUserDto;
import com.cooksys.groupfinal.dtos.UserRequestDto;
import com.cooksys.groupfinal.entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-27T13:08:39-0500",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 17.0.8.1 (Eclipse Adoptium)"
)
@Component
public class BasicUserMapperImpl implements BasicUserMapper {

    @Autowired
    private ProfileMapper profileMapper;
    @Autowired
    private CredentialsMapper credentialsMapper;

    @Override
    public BasicUserDto entityToBasicUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        BasicUserDto basicUserDto = new BasicUserDto();

        basicUserDto.setId( user.getId() );
        basicUserDto.setProfile( profileMapper.entityToDto( user.getProfile() ) );
        basicUserDto.setAdmin( user.isAdmin() );
        basicUserDto.setActive( user.isActive() );
        basicUserDto.setStatus( user.getStatus() );

        return basicUserDto;
    }

    @Override
    public List<BasicUserDto> entitiesToBasicUserDtos(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<BasicUserDto> list = new ArrayList<BasicUserDto>( users.size() );
        for ( User user : users ) {
            list.add( entityToBasicUserDto( user ) );
        }

        return list;
    }

    @Override
    public User requestDtoToEntity(UserRequestDto userRequestDto) {
        if ( userRequestDto == null ) {
            return null;
        }

        User user = new User();

        user.setCredentials( credentialsMapper.dtoToEntity( userRequestDto.getCredentials() ) );
        user.setProfile( profileMapper.dtoToEntity( userRequestDto.getProfile() ) );
        user.setAdmin( userRequestDto.isAdmin() );

        return user;
    }

    @Override
    public List<User> basicDtosToEntities(List<BasicUserDto> teammates) {
        if ( teammates == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( teammates.size() );
        for ( BasicUserDto basicUserDto : teammates ) {
            list.add( basicUserDtoToUser( basicUserDto ) );
        }

        return list;
    }

    protected User basicUserDtoToUser(BasicUserDto basicUserDto) {
        if ( basicUserDto == null ) {
            return null;
        }

        User user = new User();

        user.setId( basicUserDto.getId() );
        user.setProfile( profileMapper.dtoToEntity( basicUserDto.getProfile() ) );
        user.setActive( basicUserDto.isActive() );
        user.setAdmin( basicUserDto.isAdmin() );
        user.setStatus( basicUserDto.getStatus() );

        return user;
    }
}
