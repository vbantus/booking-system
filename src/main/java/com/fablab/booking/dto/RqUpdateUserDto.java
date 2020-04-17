package com.fablab.booking.dto;

import lombok.Data;

@Data
public class RqUpdateUserDto {
    private String username;
    private String password;
    private boolean enabled;
    private String email;
}
