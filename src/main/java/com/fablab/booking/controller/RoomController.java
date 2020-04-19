package com.fablab.booking.controller;

import com.fablab.booking.dto.RqRoomDto;
import com.fablab.booking.dto.RsRoomDto;
import com.fablab.booking.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/booking/room")
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<RsRoomDto> save(@RequestBody RqRoomDto rqRoomDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roomService.save(rqRoomDto));
    }

    @PutMapping("/{roomId}")
    public ResponseEntity<RsRoomDto> update(@PathVariable("roomId") Long roomId,
                                            @RequestBody RqRoomDto rqRoomDto) {
        return ResponseEntity.ok(roomService.update(rqRoomDto, roomId));
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<Void> deleteById(@PathVariable("roomId") Long roomId) {
        roomService.deleteById(roomId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<RsRoomDto>> getAll() {
        return ResponseEntity.ok(roomService.getAll());
    }

}
