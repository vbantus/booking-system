package com.fablab.booking.controller;

import com.fablab.booking.domain.BookingUser;
import com.fablab.booking.dto.JwtResponseDto;
import com.fablab.booking.repository.BookingUserRepository;
import com.fablab.booking.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/booking/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final BookingUserRepository bookingUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/sign-in")
    public ResponseEntity<BookingUser> registerUser(@RequestBody BookingUser bookingUser){
        bookingUser.setPassword(bCryptPasswordEncoder.encode(bookingUser.getPassword()));
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingUserRepository.save(bookingUser));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<JwtResponseDto> authenticateUser(@RequestBody BookingUser bookingUser){
        return  ResponseEntity.ok(authenticationService.authenticate(bookingUser.getUsername(), bookingUser.getPassword()));
    }
}
