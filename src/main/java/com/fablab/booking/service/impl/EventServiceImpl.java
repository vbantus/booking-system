package com.fablab.booking.service.impl;

import com.fablab.booking.domain.Event;
import com.fablab.booking.dto.RqCreateEventDto;
import com.fablab.booking.dto.RqUpdateEventDto;
import com.fablab.booking.dto.RsEventDto;
import com.fablab.booking.exception.EntityNotFoundException;
import com.fablab.booking.mapper.EventMapper;
import com.fablab.booking.repository.EventRepository;
import com.fablab.booking.service.EventService;
import com.fablab.booking.service.MinioService;
import com.fablab.booking.service.UserService;
import com.fablab.booking.service.utils.TimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    @Value("${minio.buckek.event.name}")
    private String eventBucket;

    private final EventRepository eventRepository;
    private final UserService userService;
    private final MinioService minioService;

    @Override
    public RsEventDto save(RqCreateEventDto rqCreateEventDto, MultipartFile image) {
        Event event = EventMapper.INSTANCE.rqCreateEventDtoToEvent(rqCreateEventDto);
        TimeUtils.validateDates(event.getStartTime(), event.getEndTime());

        String imageUrl = minioService.saveImage(image, eventBucket);
        event.setUser(userService.findById(rqCreateEventDto.getUserId()));
        event.setImageUrl(imageUrl);
        return EventMapper.INSTANCE.eventToRsEventDto(eventRepository.save(event));
    }

    //TODO delete unused image
    @Override
    public RsEventDto update(RqUpdateEventDto rqUpdateEventDto, MultipartFile image, Long id) {
        Event event = findById(id);
        EventMapper.INSTANCE.updateEventFromRqUpdateEventDto(rqUpdateEventDto, event);
        TimeUtils.validateDates(event.getStartTime(), event.getEndTime());


        if (image != null) {
            String imageUrl = minioService.saveImage(image, eventBucket);
            event.setImageUrl(imageUrl);
        }
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
    public List<RsEventDto> getAll(Pageable pageable) {
        return eventRepository.findAll(pageable).stream()
                .map(EventMapper.INSTANCE::eventToRsEventDto)
                .collect(Collectors.toList());
    }

    @Override
    public RsEventDto getById(Long id) {
        Event event = findById(id);
        return EventMapper.INSTANCE.eventToRsEventDto(event);
    }

    @Override
    public Long count() {
        return eventRepository.count();
    }

    @Override
    public List<RsEventDto> getAllUpcomingEvents(Pageable pageable) {
        return eventRepository.findAllUpcomingEvents(pageable).stream()
                .map(EventMapper.INSTANCE::eventToRsEventDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RsEventDto> getAllPastEvents(Pageable pageable) {
        return eventRepository.findAllPastEvents(pageable).stream()
                .map(EventMapper.INSTANCE::eventToRsEventDto)
                .collect(Collectors.toList());
    }

    @Override
    public Event findById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("event not found by id: " + id));
    }
}
