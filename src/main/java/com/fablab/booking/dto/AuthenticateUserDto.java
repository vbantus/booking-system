package com.fablab.booking.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AuthenticateUserDto {
    @ApiModelProperty(value = "username", example = "user")
    private String username;
    @ApiModelProperty(value = "password", example = "user")
    private String password;
}
