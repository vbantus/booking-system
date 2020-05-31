package com.fablab.booking.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RqUpdateUserDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private boolean enabled;
    private String email;
}
