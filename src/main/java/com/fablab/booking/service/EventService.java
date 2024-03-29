package com.fablab.booking.service;

import com.fablab.booking.domain.Event;
import com.fablab.booking.dto.RqCreateEventDto;
import com.fablab.booking.dto.RqUpdateEventDto;
import com.fablab.booking.dto.RsEventDto;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EventService {

    RsEventDto save(RqCreateEventDto rqCreateEventDto, MultipartFile image);

    RsEventDto update(RqUpdateEventDto rqUpdateEventDto, MultipartFile image, Long id);

    void deleteById(Long id);

    List<RsEventDto> getAllByUserId(Long userId);

    List<RsEventDto> getAll(Pageable pageable);

    RsEventDto getById(Long id);

    Long count();

    List<RsEventDto> getAllUpcomingEvents(Pageable pageable);

    List<RsEventDto> getAllPastEvents(Pageable pageable);

    Event findById(Long id);
}
