package com.fablab.booking.controller;

import com.fablab.booking.dto.RqBookingSpaceDto;
import com.fablab.booking.dto.RsBookingSpaceDto;
import com.fablab.booking.service.BookingSpaceService;
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
@RequestMapping("/booking/api/booking-space")
public class BookingSpaceController {

    private final BookingSpaceService bookingSpaceService;

    @PostMapping
    public ResponseEntity<RsBookingSpaceDto> save(@RequestBody RqBookingSpaceDto rqBookingSpaceDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingSpaceService.save(rqBookingSpaceDto));
    }

    @PutMapping("/{bookingSpaceId}")
    public ResponseEntity<RsBookingSpaceDto> update(@PathVariable("bookingSpaceId") Long bookingSpaceId,
                                                    @RequestBody RqBookingSpaceDto rqBookingSpaceDto) {
        return ResponseEntity.ok(bookingSpaceService.update(rqBookingSpaceDto, bookingSpaceId));
    }

    @DeleteMapping("/{bookingSpaceId}")
    public ResponseEntity<Void> deleteById(@PathVariable("bookingSpaceId") Long bookingSpaceId) {
        bookingSpaceService.deleteById(bookingSpaceId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<RsBookingSpaceDto>> getAll() {
        return ResponseEntity.ok(bookingSpaceService.findAll());
    }

}
