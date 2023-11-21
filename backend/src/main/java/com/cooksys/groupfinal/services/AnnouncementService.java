package com.cooksys.groupfinal.services;

import com.cooksys.groupfinal.dtos.AnnouncementDto;
import com.cooksys.groupfinal.dtos.AnnouncementRequestDto;

public interface AnnouncementService {

    AnnouncementDto addAnnouncement(Long companyId, AnnouncementRequestDto announcementRequest);
}
