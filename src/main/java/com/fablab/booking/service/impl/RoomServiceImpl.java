package com.fablab.booking.service.impl;

import com.fablab.booking.domain.Room;
import com.fablab.booking.domain.common.exception.EntityNotFoundException;
import com.fablab.booking.dto.RqRoomDto;
import com.fablab.booking.dto.RsRoomDto;
import com.fablab.booking.mapper.RoomMapper;
import com.fablab.booking.repository.RoomRepository;
import com.fablab.booking.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public RsRoomDto save(RqRoomDto rqRoomDto) {
        Room room = RoomMapper.INSTANCE.rqCreateBookingSpaceDtoToBookingSpace(rqRoomDto);
        return RoomMapper.INSTANCE.bookingSpaceToRsBookingSpaceDto(roomRepository.save(room));
    }

    @Override
    public RsRoomDto update(RqRoomDto rqRoomDto, Long id) {
        Room room = findById(id);
        RoomMapper.INSTANCE.updateBookingSpaceFromRqBookingSpaceDto(rqRoomDto, room);
        return RoomMapper.INSTANCE.bookingSpaceToRsBookingSpaceDto(roomRepository.save(room));
    }

    @Override
    public void deleteById(Long id) {
        roomRepository.deleteById(id);
    }

    @Override
    public List<RsRoomDto> getAll() {
        return roomRepository.findAll().stream()
                .map(RoomMapper.INSTANCE::bookingSpaceToRsBookingSpaceDto)
                .collect(Collectors.toList());
    }

    @Override
    public Room findById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("hall not found by id: " + id));
    }
}
