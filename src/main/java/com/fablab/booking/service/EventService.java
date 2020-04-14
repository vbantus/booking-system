package com.fablab.booking.service;

import com.fablab.booking.dto.RqCreateEventDto;
import com.fablab.booking.dto.RqUpdateEventDto;
import com.fablab.booking.dto.RsEventDto;

import java.util.List;

public interface EventService {

    RsEventDto save(RqCreateEventDto rqCreateEventDto);

    RsEventDto update(RqUpdateEventDto rqUpdateEventDto, Long id);

    void deleteById(Long id);

    List<RsEventDto> findAllDtoByUserId(Long userId);

    List<RsEventDto> findAll();

}
