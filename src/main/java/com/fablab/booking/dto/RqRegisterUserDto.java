package com.fablab.booking.dto;

import lombok.Data;

@Data
public class RqRegisterUserDto {
    private String username;
    private String password;
    private boolean enabled;
    private String email;
    private String role;
}
