package com.fablab.booking.dto;

import lombok.Data;

@Data
public class RqRegisterUserDto {
    private String username;
    private String password;
    private String email;
}
