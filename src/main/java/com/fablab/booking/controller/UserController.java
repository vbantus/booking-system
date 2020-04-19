package com.fablab.booking.controller;

import com.fablab.booking.dto.*;
import com.fablab.booking.service.ArticleService;
import com.fablab.booking.service.EventService;
import com.fablab.booking.service.RoomBookingService;
import com.fablab.booking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/booking/user")
public class UserController {

    private final UserService userService;
    private final ArticleService articleService;
    private final RoomBookingService roomBookingService;
    private final EventService eventService;

    @PutMapping("/{userId}")
    public ResponseEntity<RsUserDto> update(@PathVariable("userId") Long userId,
                                            @RequestBody RqUpdateUserDto rqUpdateUserDto) {
        return ResponseEntity.ok(userService.update(rqUpdateUserDto, userId));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteById(@PathVariable("userId") Long userId) {
        userService.deleteById(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<RsUserDto>> getAll(){
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{username}")
    public ResponseEntity<RsUserDto> getByUsername(@PathVariable("username") String username) {
        return ResponseEntity.ok(userService.findByUsername(username));
    }

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
    public ResponseEntity<List<RsRoomBookingDto>> getAllPendingBookingsByUserId(@PathVariable("userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(roomBookingService.getAllPendingBookingsByUserId(userId));
    }

    @GetMapping("/{userId}/active-bookings")
    public ResponseEntity<List<RsRoomBookingDto>> getAllActiveBookingsByUserId(@PathVariable("userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(roomBookingService.getAllActiveBookingsByUserId(userId));
    }

    @GetMapping("/{userId}/expired-bookings")
    public ResponseEntity<List<RsRoomBookingDto>> getAllExpiredBookingsByUserId(@PathVariable("userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(roomBookingService.getAllExpiredBookingsByUserId(userId));
    }
}
