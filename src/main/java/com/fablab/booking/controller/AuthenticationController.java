package com.fablab.booking.controller;

import com.fablab.booking.domain.BookingUser;
import com.fablab.booking.dto.AuthenticateUserDto;
import com.fablab.booking.dto.JwtResponseDto;
import com.fablab.booking.dto.RqRegisterUserDto;
import com.fablab.booking.dto.RsUserDto;
import com.fablab.booking.service.AuthenticationService;
import com.fablab.booking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/booking/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping("/sign-in")
    public ResponseEntity<RsUserDto> registerUser(@RequestBody RqRegisterUserDto rqRegisterUserDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(rqRegisterUserDto));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<JwtResponseDto> authenticateUser(@RequestBody AuthenticateUserDto authenticateUserDto) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticateUserDto.getUsername(), authenticateUserDto.getPassword()));
    }
}
