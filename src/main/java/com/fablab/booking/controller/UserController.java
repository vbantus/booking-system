package com.fablab.booking.controller;

import com.fablab.booking.dto.RsArticleDto;
import com.fablab.booking.dto.RsBookingSpaceRelationDto;
import com.fablab.booking.service.ArticleService;
import com.fablab.booking.service.BookingSpaceRelationService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
    private final BookingSpaceRelationService bookingSpaceRelationService;

    @GetMapping("/{userId}/articles")
    public ResponseEntity<List<RsArticleDto>> getAllArticlesByUserId(@PathVariable("userId") Long userId,
                                                                     @ApiIgnore Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(articleService.findAllDtoByUserId(userId, pageable));
    }

    @GetMapping("/{userId}/pending-bookings")
    public ResponseEntity<List<RsBookingSpaceRelationDto>> getAllPendingBookingsByUserId(@PathVariable("userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(bookingSpaceRelationService.findAllPendingBookingsByUserId(userId));
    }

    @GetMapping("/{userId}/active-bookings")
    public ResponseEntity<List<RsBookingSpaceRelationDto>> getAllActiveBookingsByUserId(@PathVariable("userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(bookingSpaceRelationService.findAllActiveBookingsByUserId(userId));
    }

    @GetMapping("/{userId}/expired-bookings")
    public ResponseEntity<List<RsBookingSpaceRelationDto>> getAllExpiredBookingsByUserId(@PathVariable("userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(bookingSpaceRelationService.findAllExpiredBookingsByUserId(userId));
    }
}
