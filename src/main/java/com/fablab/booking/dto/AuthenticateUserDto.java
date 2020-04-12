package com.fablab.booking.dto;

import lombok.Data;

@Data
public class AuthenticateUserDto {
    private String username;
    private String password;
}
