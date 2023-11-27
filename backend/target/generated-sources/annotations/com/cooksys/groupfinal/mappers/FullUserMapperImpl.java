package com.cooksys.groupfinal.mappers;

import com.cooksys.groupfinal.dtos.FullUserDto;
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
public class FullUserMapperImpl implements FullUserMapper {

    @Autowired
    private ProfileMapper profileMapper;
    @Autowired
    private CredentialsMapper credentialsMapper;
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private TeamMapper teamMapper;

    @Override
    public FullUserDto entityToFullUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        FullUserDto fullUserDto = new FullUserDto();

        fullUserDto.setId( user.getId() );
        fullUserDto.setProfile( profileMapper.entityToDto( user.getProfile() ) );
        fullUserDto.setAdmin( user.isAdmin() );
        fullUserDto.setActive( user.isActive() );
        fullUserDto.setStatus( user.getStatus() );
        fullUserDto.setCompanies( companyMapper.entitiesToDtos( user.getCompanies() ) );
        fullUserDto.setTeams( teamMapper.entitiesToDtos( user.getTeams() ) );

        return fullUserDto;
    }

    @Override
    public List<FullUserDto> entitiesToFullUserDtos(List<User> filteredUsers) {
        if ( filteredUsers == null ) {
            return null;
        }

        List<FullUserDto> list = new ArrayList<FullUserDto>( filteredUsers.size() );
        for ( User user : filteredUsers ) {
            list.add( entityToFullUserDto( user ) );
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
}
