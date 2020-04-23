package com.fablab.booking.controller;

import com.fablab.booking.repository.RoomBookingRepository;
import com.fablab.booking.repository.UserRepository;
import com.fablab.booking.service.ArticleService;
import com.fablab.booking.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@RestController
//@CrossOrigin
@RequestMapping("/booking/test")
public class IndexController {

    private final ArticleService articleService;
    private final CommentService commentService;
    private final UserRepository userRepository;
    private final EntityManager entityManager;
    private final RoomBookingRepository roomBookingRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<String> hi() {
        roomBookingRepository.findAllActiveBookings();
        return ResponseEntity.status(HttpStatus.OK).body("hi there");
    }
}
