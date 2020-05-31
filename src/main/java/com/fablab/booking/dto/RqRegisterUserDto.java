package com.fablab.booking.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RqRegisterUserDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private String email;
}
