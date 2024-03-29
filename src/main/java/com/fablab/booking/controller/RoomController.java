package com.fablab.booking.controller;

import com.fablab.booking.dto.RqRoomDto;
import com.fablab.booking.dto.RsRoomDto;
import com.fablab.booking.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/booking/rooms")
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<RsRoomDto> save(@Valid RqRoomDto rqRoomDto,
                                          @RequestParam(value = "image", required = false) MultipartFile image) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roomService.save(rqRoomDto, image));
    }

    @PatchMapping("/{roomId}")
    public ResponseEntity<RsRoomDto> update(@Valid RqRoomDto rqRoomDto,
                                            @RequestParam(value = "image", required = false) MultipartFile image,
                                            @PathVariable("roomId") Long roomId) {
        return ResponseEntity.ok(roomService.update(rqRoomDto, image, roomId));
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

    @GetMapping("/{roomId}")
    public ResponseEntity<RsRoomDto> getById(@PathVariable("roomId") Long roomId) {
        return ResponseEntity.status(HttpStatus.OK).body(roomService.getById(roomId));
    }

}
