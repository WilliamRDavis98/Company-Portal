package com.cooksys.groupfinal.mappers;

import com.cooksys.groupfinal.dtos.BasicUserDto;
import com.cooksys.groupfinal.dtos.CompanyDto;
import com.cooksys.groupfinal.dtos.FullUserDto;
import com.cooksys.groupfinal.entities.Company;
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
public class UserMapperImpl implements UserMapper {

    @Autowired
    private ProfileMapper profileMapper;
    @Autowired
    private TeamMapper teamMapper;

    @Override
    public FullUserDto entityToDto(User user) {
        if ( user == null ) {
            return null;
        }

        FullUserDto fullUserDto = new FullUserDto();

        fullUserDto.setProfile( profileMapper.entityToDto( user.getProfile() ) );
        fullUserDto.setId( user.getId() );
        fullUserDto.setAdmin( user.isAdmin() );
        fullUserDto.setActive( user.isActive() );
        fullUserDto.setStatus( user.getStatus() );
        fullUserDto.setCompanies( companyListToCompanyDtoList( user.getCompanies() ) );
        fullUserDto.setTeams( teamMapper.entitiesToDtos( user.getTeams() ) );

        return fullUserDto;
    }

    @Override
    public List<FullUserDto> entitiesToDtos(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<FullUserDto> list = new ArrayList<FullUserDto>( users.size() );
        for ( User user : users ) {
            list.add( entityToDto( user ) );
        }

        return list;
    }

    protected BasicUserDto userToBasicUserDto(User user) {
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

    protected List<BasicUserDto> userListToBasicUserDtoList(List<User> list) {
        if ( list == null ) {
            return null;
        }

        List<BasicUserDto> list1 = new ArrayList<BasicUserDto>( list.size() );
        for ( User user : list ) {
            list1.add( userToBasicUserDto( user ) );
        }

        return list1;
    }

    protected CompanyDto companyToCompanyDto(Company company) {
        if ( company == null ) {
            return null;
        }

        CompanyDto companyDto = new CompanyDto();

        companyDto.setId( company.getId() );
        companyDto.setName( company.getName() );
        companyDto.setDescription( company.getDescription() );
        companyDto.setTeams( teamMapper.entitiesToDtos( company.getTeams() ) );
        companyDto.setEmployees( userListToBasicUserDtoList( company.getEmployees() ) );

        return companyDto;
    }

    protected List<CompanyDto> companyListToCompanyDtoList(List<Company> list) {
        if ( list == null ) {
            return null;
        }

        List<CompanyDto> list1 = new ArrayList<CompanyDto>( list.size() );
        for ( Company company : list ) {
            list1.add( companyToCompanyDto( company ) );
        }

        return list1;
    }
}
