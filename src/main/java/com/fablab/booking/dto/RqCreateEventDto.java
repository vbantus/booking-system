package com.fablab.booking.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;

@Data
public class RqCreateEventDto {
    @ApiModelProperty(value = "title", example = "FABLAB event")
    private String title;
    @ApiModelProperty(value = "description", example = "about FABLAB")
    private String description;
    private String location;
    private int participationFee;
    private Date startTime;
    private Date endTime;
    @ApiModelProperty(value = "userId", example = "1")
    private Long userId;
}
