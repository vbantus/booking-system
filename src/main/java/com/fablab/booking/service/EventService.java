package com.fablab.booking.service;

import com.fablab.booking.domain.Event;
import com.fablab.booking.dto.RqCreateEventDto;
import com.fablab.booking.dto.RqUpdateEventDto;
import com.fablab.booking.dto.RsEventDto;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EventService {

    RsEventDto save(MultipartFile image, RqCreateEventDto rqCreateEventDto);

    RsEventDto update(Long id, MultipartFile image, RqUpdateEventDto rqUpdateEventDto);

    void deleteById(Long id);

    List<RsEventDto> getAllByUserId(Long userId);

    List<RsEventDto> getAll(Pageable pageable);

    RsEventDto getById(Long id);

    Event findById(Long id);
}
