package com.cooksys.groupfinal.controllers;

import com.cooksys.groupfinal.dtos.AnnouncementDto;
import com.cooksys.groupfinal.dtos.AnnouncementRequestDto;
import org.springframework.web.bind.annotation.*;

import com.cooksys.groupfinal.services.AnnouncementService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/announcements")
@RequiredArgsConstructor
@CrossOrigin(origins="*")
public class AnnouncementController {
	
	private final AnnouncementService announcementService;

	@PostMapping("/{companyId}")
	public AnnouncementDto addAnnouncement(@PathVariable Long companyId, @RequestBody AnnouncementRequestDto announcementRequest) {
		return announcementService.addAnnouncement(companyId, announcementRequest);
	}

}
