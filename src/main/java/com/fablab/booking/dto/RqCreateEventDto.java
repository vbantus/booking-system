package com.fablab.booking.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class RqCreateEventDto {
    @ApiModelProperty(value = "title", example = "FABLAB event")
    private String title;
    @ApiModelProperty(value = "description", example = "about FABLAB")
    private String description;
    private String location;
    private int participationFee;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ssZ") //validate date when it is sent as requestParam
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ssZ") //validate date when it is sent as json
    @ApiModelProperty(value = "time when event starts, should follow pattern: yyyy-MM-dd HH:mm:ssZ", example = "2020-04-20 09:00:00+0000")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ssZ")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ssZ")
    @ApiModelProperty(value = "time when event ends, should follow pattern: yyyy-MM-dd HH:mm:ssZ", example = "2020-04-20 10:00:00+0000")
    private Date endTime;
    @ApiModelProperty(value = "userId", example = "1")
    private Long userId;
}
