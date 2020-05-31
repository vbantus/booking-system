package com.fablab.booking.controller;

import com.fablab.booking.dto.RqRoomBookingDto;
import com.fablab.booking.dto.RsRoomBookingDto;
import com.fablab.booking.service.RoomBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/booking/rooms-booking")
public class RoomBookingController {

    private final RoomBookingService roomBookingService;


    @PostMapping
    public ResponseEntity<RsRoomBookingDto> save(@Valid @RequestBody RqRoomBookingDto rqRoomBookingDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roomBookingService.save(rqRoomBookingDto));
    }

    @PutMapping("/{roomBookingId}")
    public ResponseEntity<RsRoomBookingDto> update(@Valid @RequestBody RqRoomBookingDto rqRoomBookingDto,
                                                   @PathVariable("roomBookingId") Long roomBookingId) {
        return ResponseEntity.ok(roomBookingService.update(rqRoomBookingDto, roomBookingId));
    }

    @DeleteMapping("/{roomBookingId}")
    public ResponseEntity<Void> deleteById(@PathVariable("roomBookingId") Long roomBookingId) {
        roomBookingService.deleteById(roomBookingId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<RsRoomBookingDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(roomBookingService.getAll());
    }

    @GetMapping("/pending")
    public ResponseEntity<List<RsRoomBookingDto>> getAllPendingBookings() {
        return ResponseEntity.status(HttpStatus.OK).body(roomBookingService.getAllPendingBookings());
    }

    @GetMapping("/active")
    public ResponseEntity<List<RsRoomBookingDto>> getAllActiveBookings() {
        return ResponseEntity.status(HttpStatus.OK).body(roomBookingService.getAllActiveBookings());
    }

    @GetMapping("/expired")
    public ResponseEntity<List<RsRoomBookingDto>> getAllExpiredBookings() {
        return ResponseEntity.status(HttpStatus.OK).body(roomBookingService.getAllExpiredBookings());
    }

}
