package com.fablab.booking.controller;

import com.fablab.booking.dto.RqBookingSpaceRelationDto;
import com.fablab.booking.dto.RsBookingSpaceRelationDto;
import com.fablab.booking.service.BookingSpaceRelationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/booking/api/booking-space-relation")
public class BookingSpaceRelationController {

    private final BookingSpaceRelationService bookingSpaceRelationService;

    @PostMapping
    public ResponseEntity<RsBookingSpaceRelationDto> save(@RequestBody RqBookingSpaceRelationDto rqBookingSpaceRelationDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingSpaceRelationService.save(rqBookingSpaceRelationDto));
    }

    @PutMapping("/{bookingSpaceRelationId}")
    public ResponseEntity<RsBookingSpaceRelationDto> update(@PathVariable("bookingSpaceRelationId") Long bookingSpaceRelationId,
                                               @RequestBody RqBookingSpaceRelationDto rqBookingSpaceRelationDto) {
        return ResponseEntity.ok(bookingSpaceRelationService.update(rqBookingSpaceRelationDto,bookingSpaceRelationId));
    }

    @DeleteMapping("/{bookingSpaceRelationId}")
    public ResponseEntity<Void> deleteById(@PathVariable("bookingSpaceRelationId") Long bookingSpaceRelationId) {
        bookingSpaceRelationService.deleteById(bookingSpaceRelationId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
