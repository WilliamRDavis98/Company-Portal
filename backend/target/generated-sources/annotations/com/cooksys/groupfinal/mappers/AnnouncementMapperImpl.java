package com.cooksys.groupfinal.mappers;

import com.cooksys.groupfinal.dtos.AnnouncementDto;
import com.cooksys.groupfinal.dtos.AnnouncementRequestDto;
import com.cooksys.groupfinal.entities.Announcement;
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
public class AnnouncementMapperImpl implements AnnouncementMapper {

    @Autowired
    private BasicUserMapper basicUserMapper;

    @Override
    public AnnouncementDto entityToDto(Announcement announcement) {
        if ( announcement == null ) {
            return null;
        }

        AnnouncementDto announcementDto = new AnnouncementDto();

        announcementDto.setId( announcement.getId() );
        announcementDto.setDate( announcement.getDate() );
        announcementDto.setTitle( announcement.getTitle() );
        announcementDto.setMessage( announcement.getMessage() );
        announcementDto.setAuthor( basicUserMapper.entityToBasicUserDto( announcement.getAuthor() ) );

        return announcementDto;
    }

    @Override
    public Announcement requestToEntity(AnnouncementRequestDto announcementRequest) {
        if ( announcementRequest == null ) {
            return null;
        }

        Announcement announcement = new Announcement();

        announcement.setTitle( announcementRequest.getTitle() );
        announcement.setMessage( announcementRequest.getMessage() );

        return announcement;
    }

    @Override
    public List<AnnouncementDto> entitiesToDtos(List<Announcement> sortedList2) {
        if ( sortedList2 == null ) {
            return null;
        }

        List<AnnouncementDto> list = new ArrayList<AnnouncementDto>( sortedList2.size() );
        for ( Announcement announcement : sortedList2 ) {
            list.add( entityToDto( announcement ) );
        }

        return list;
    }
}
