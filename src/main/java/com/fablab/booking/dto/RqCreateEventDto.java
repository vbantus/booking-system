package com.fablab.booking.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static com.fablab.booking.service.utils.TimeUtils.DATE_PATTERN;
import static com.fablab.booking.service.utils.TimeUtils.DATE_REG_EXP;

@Data
public class RqCreateEventDto {
    @NotBlank
    @ApiModelProperty(value = "title", example = "FABLAB event")
    private String title;
    @NotBlank
    @ApiModelProperty(value = "description", example = "about FABLAB")
    private String description;
    private String location;
    private int participationFee;
    @NotBlank
    @Pattern(regexp = DATE_REG_EXP, message = "date should follow pattern: " + DATE_PATTERN)
    @ApiModelProperty(value = "time when event starts, should follow pattern: " + DATE_PATTERN, example = "2020-04-20 09:00:00+0000")
    private String startTime;
    @NotBlank
    @Pattern(regexp = DATE_REG_EXP, message = "date should follow pattern: " + DATE_PATTERN)
    @ApiModelProperty(value = "time when event ends, should follow pattern: " + DATE_PATTERN, example = "2020-04-20 10:00:00+0000")
    private String endTime;
    @NotNull
    @ApiModelProperty(value = "userId", example = "1")
    private Long userId;
}
