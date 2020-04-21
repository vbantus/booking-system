package com.fablab.booking.service.impl;

import com.fablab.booking.domain.Room;
import com.fablab.booking.dto.RsRoomDto;
import com.fablab.booking.repository.RoomRepository;
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
class RoomServiceImplTest {

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private RoomServiceImpl roomService;

    @Test
    void testGetAll() {
        Room room = Room.builder()
                .name("roomName")
                .description("roomDescription")
                .pricePerHour(75)
                .build();

        when(roomRepository.findAll()).thenReturn(Collections.singletonList(room));

        List<RsRoomDto> roomDtoList = roomService.getAll();

        verify(roomRepository).findAll();
        assertThat(roomDtoList.size()).isEqualTo(1);
        assertThat(roomDtoList.get(0).getName()).isEqualTo(room.getName());
        assertThat(roomDtoList.get(0).getDescription()).isEqualTo(room.getDescription());
        assertThat(roomDtoList.get(0).getPricePerHour()).isEqualTo(room.getPricePerHour());
    }
}
