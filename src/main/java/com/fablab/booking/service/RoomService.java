package com.fablab.booking.service;

import com.fablab.booking.domain.Room;
import com.fablab.booking.dto.RqRoomDto;
import com.fablab.booking.dto.RsRoomDto;

import java.util.List;

public interface RoomService {

    RsRoomDto save(RqRoomDto rqRoomDto);

    RsRoomDto update(RqRoomDto rqRoomDto, Long id);

    void deleteById(Long id);

    List<RsRoomDto> getAll();

    Room findById(Long id);
}
