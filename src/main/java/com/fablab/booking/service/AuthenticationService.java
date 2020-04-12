package com.fablab.booking.service;

import com.fablab.booking.dto.JwtResponseDto;

public interface AuthenticationService {
    JwtResponseDto authenticate(String username, String password);
}
