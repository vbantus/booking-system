package com.fablab.booking.dto;

import lombok.Data;

@Data
public class RsUserDto extends AbstractResponseEntityDto {
    private String username;
    private String email;
    private String role;
}
