package com.fablab.booking.controller;

import com.fablab.booking.dto.RsArticleDto;
import com.fablab.booking.dto.RsBookingSpaceRelationDto;
import com.fablab.booking.dto.RsEventDto;
import com.fablab.booking.service.ArticleService;
import com.fablab.booking.service.RoomBookingService;
import com.fablab.booking.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/booking/api/user")
public class UserController {

    private final ArticleService articleService;
    private final RoomBookingService roomBookingService;
    private final EventService eventService;

    @GetMapping("/{userId}/events")
    public ResponseEntity<List<RsEventDto>> getAllEventsByUserId(@PathVariable("userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(eventService.getAllByUserId(userId));
    }

    @GetMapping("/{userId}/articles")
    public ResponseEntity<List<RsArticleDto>> getAllArticlesByUserId(@PathVariable("userId") Long userId,
                                                                     @ApiIgnore Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(articleService.getAllByUserId(userId, pageable));
    }

    @GetMapping("/{userId}/pending-bookings")
    public ResponseEntity<List<RsBookingSpaceRelationDto>> getAllPendingBookingsByUserId(@PathVariable("userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(roomBookingService.getAllPendingBookingsByUserId(userId));
    }

    @GetMapping("/{userId}/active-bookings")
    public ResponseEntity<List<RsBookingSpaceRelationDto>> getAllActiveBookingsByUserId(@PathVariable("userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(roomBookingService.getAllActiveBookingsByUserId(userId));
    }

    @GetMapping("/{userId}/expired-bookings")
    public ResponseEntity<List<RsBookingSpaceRelationDto>> getAllExpiredBookingsByUserId(@PathVariable("userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(roomBookingService.getAllExpiredBookingsByUserId(userId));
    }
}
