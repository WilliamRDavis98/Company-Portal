package com.cooksys.groupfinal.mappers;

import com.cooksys.groupfinal.dtos.CompanyDto;
import com.cooksys.groupfinal.entities.Company;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-27T13:08:40-0500",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 17.0.8.1 (Eclipse Adoptium)"
)
@Component
public class CompanyMapperImpl implements CompanyMapper {

    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private BasicUserMapper basicUserMapper;

    @Override
    public CompanyDto entityToDto(Company company) {
        if ( company == null ) {
            return null;
        }

        CompanyDto companyDto = new CompanyDto();

        companyDto.setId( company.getId() );
        companyDto.setName( company.getName() );
        companyDto.setDescription( company.getDescription() );
        companyDto.setTeams( teamMapper.entitiesToDtos( company.getTeams() ) );
        companyDto.setEmployees( basicUserMapper.entitiesToBasicUserDtos( company.getEmployees() ) );

        return companyDto;
    }

    @Override
    public List<CompanyDto> entitiesToDtos(List<Company> companies) {
        if ( companies == null ) {
            return null;
        }

        List<CompanyDto> list = new ArrayList<CompanyDto>( companies.size() );
        for ( Company company : companies ) {
            list.add( entityToDto( company ) );
        }

        return list;
    }
}
