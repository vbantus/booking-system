package com.fablab.booking.controller;

import com.fablab.booking.dto.RqRoomBookingDto;
import com.fablab.booking.dto.RsRoomBookingDto;
import com.fablab.booking.service.RoomBookingService;
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
@RequestMapping("/booking/api/room-booking")
public class RoomBookingController {

    private final RoomBookingService roomBookingService;


    @PostMapping
    public ResponseEntity<RsRoomBookingDto> save(@RequestBody RqRoomBookingDto rqRoomBookingDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roomBookingService.save(rqRoomBookingDto));
    }

    @PutMapping("/{roomBookingId}")
    public ResponseEntity<RsRoomBookingDto> update(@PathVariable("roomBookingId") Long roomBookingId,
                                                   @RequestBody RqRoomBookingDto rqRoomBookingDto) {
        return ResponseEntity.ok(roomBookingService.update(rqRoomBookingDto,roomBookingId));
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
