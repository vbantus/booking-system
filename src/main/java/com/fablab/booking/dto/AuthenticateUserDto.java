package com.fablab.booking.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AuthenticateUserDto {
    @NotBlank
    @ApiModelProperty(value = "username", example = "user")
    private String username;
    @NotBlank
    @ApiModelProperty(value = "password", example = "user")
    private String password;
}
