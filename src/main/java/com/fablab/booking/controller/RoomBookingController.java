package com.fablab.booking.controller;

import com.fablab.booking.dto.RqBookingSpaceRelationDto;
import com.fablab.booking.dto.RsBookingSpaceRelationDto;
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
@RequestMapping("/booking/api/booking-space-relation")
public class RoomBookingController {

    private final RoomBookingService roomBookingService;


    @PostMapping
    public ResponseEntity<RsBookingSpaceRelationDto> save(@RequestBody RqBookingSpaceRelationDto rqBookingSpaceRelationDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roomBookingService.save(rqBookingSpaceRelationDto));
    }

    @PutMapping("/{bookingSpaceRelationId}")
    public ResponseEntity<RsBookingSpaceRelationDto> update(@PathVariable("bookingSpaceRelationId") Long bookingSpaceRelationId,
                                                            @RequestBody RqBookingSpaceRelationDto rqBookingSpaceRelationDto) {
        return ResponseEntity.ok(roomBookingService.update(rqBookingSpaceRelationDto,bookingSpaceRelationId));
    }

    @DeleteMapping("/{bookingSpaceRelationId}")
    public ResponseEntity<Void> deleteById(@PathVariable("bookingSpaceRelationId") Long bookingSpaceRelationId) {
        roomBookingService.deleteById(bookingSpaceRelationId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<RsBookingSpaceRelationDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(roomBookingService.getAll());
    }

    @GetMapping("/pending")
    public ResponseEntity<List<RsBookingSpaceRelationDto>> getAllPendingBookings() {
        return ResponseEntity.status(HttpStatus.OK).body(roomBookingService.getAllPendingBookings());
    }

    @GetMapping("/active")
    public ResponseEntity<List<RsBookingSpaceRelationDto>> getAllActiveBookings() {
        return ResponseEntity.status(HttpStatus.OK).body(roomBookingService.getAllActiveBookings());
    }

    @GetMapping("/expired")
    public ResponseEntity<List<RsBookingSpaceRelationDto>> getAllExpiredBookings() {
        return ResponseEntity.status(HttpStatus.OK).body(roomBookingService.getAllExpiredBookings());
    }

}
