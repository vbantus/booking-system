package com.fablab.booking.service.impl;

import com.fablab.booking.domain.Event;
import com.fablab.booking.dto.RsEventDto;
import com.fablab.booking.repository.EventRepository;
import com.fablab.booking.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventServiceImplTest {

    @Mock
    private EventRepository eventRepository;
    @Mock
    private UserService userService;
    @InjectMocks
    private EventServiceImpl eventService;

    @Test
    void getAllByUserId() {
    }

    @Test
    void testGetAll() {
        Event event = Event.builder()
                .title("eventTitle")
                .description("eventDescription")
                .entryCost(70)
                .build();

        when(eventRepository.findAll()).thenReturn(Collections.singletonList(event));

        List<RsEventDto> eventDtoList = eventService.getAll();

        verify(eventRepository).findAll();
        assertThat(eventDtoList.size()).isEqualTo(1);
        assertThat(eventDtoList.get(0).getTitle()).isEqualTo(event.getTitle());
        assertThat(eventDtoList.get(0).getDescription()).isEqualTo(event.getDescription());
        assertThat(eventDtoList.get(0).getEntryCost()).isEqualTo(event.getEntryCost());
    }
}
