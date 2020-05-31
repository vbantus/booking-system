package com.fablab.booking.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RqRoomDto {
    @NotBlank
    @ApiModelProperty(value = "name", example = "room 1")
    private String name;
    @ApiModelProperty(value = "pricePerHour", example = "15")
    private int pricePerHour;
    @ApiModelProperty(value = "description", example = "room 1 description")
    private String description;
    private String imagePath;
}
