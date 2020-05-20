package com.fablab.booking.service.impl;

import com.fablab.booking.domain.BookingUser;
import com.fablab.booking.domain.Event;
import com.fablab.booking.domain.common.exception.EntityNotFoundException;
import com.fablab.booking.dto.RqCreateEventDto;
import com.fablab.booking.dto.RqUpdateEventDto;
import com.fablab.booking.dto.RsEventDto;
import com.fablab.booking.mapper.EventMapper;
import com.fablab.booking.repository.EventRepository;
import com.fablab.booking.service.EventService;
import com.fablab.booking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final UserService userService;

    @Override
    public RsEventDto save(RqCreateEventDto rqCreateEventDto) {
        Event event = EventMapper.INSTANCE.rqCreateEventDtoToEvent(rqCreateEventDto);
        BookingUser user = userService.findById(rqCreateEventDto.getUserId());
        event.setUser(user);
        return EventMapper.INSTANCE.eventToRsEventDto(eventRepository.save(event));
    }

    @Override
    public RsEventDto update(RqUpdateEventDto rqUpdateEventDto, Long id) {
        Event event = eventRepository.findById(id).get();
        EventMapper.INSTANCE.updateEventFromRqUpdateEventDto(rqUpdateEventDto, event);
        return EventMapper.INSTANCE.eventToRsEventDto(eventRepository.save(event));
    }

    @Override
    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public List<RsEventDto> getAllByUserId(Long userId) {
        return eventRepository.findAllByUserId(userId).stream()
                .map(EventMapper.INSTANCE::eventToRsEventDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RsEventDto> getAll() {
        return eventRepository.findAll().stream()
                .map(EventMapper.INSTANCE::eventToRsEventDto)
                .collect(Collectors.toList());
    }

    @Override
    public RsEventDto getById(Long id) {
        Event event = findById(id);
        return EventMapper.INSTANCE.eventToRsEventDto(event);
    }

    @Override
    public Event findById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("event not found by id: " + id));
    }
}
