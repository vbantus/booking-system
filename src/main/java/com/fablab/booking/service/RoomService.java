package com.fablab.booking.service;

import com.fablab.booking.domain.Room;
import com.fablab.booking.dto.RqRoomDto;
import com.fablab.booking.dto.RsRoomDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RoomService {

    RsRoomDto save(RqRoomDto rqRoomDto, MultipartFile image);

    RsRoomDto update(RqRoomDto rqRoomDto, MultipartFile image, Long id);

    void deleteById(Long id);

    List<RsRoomDto> getAll();

    RsRoomDto getById(Long id);

    Room findById(Long id);
}
