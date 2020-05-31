package com.fablab.booking.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static com.fablab.booking.service.utils.TimeUtils.DATE_PATTERN;
import static com.fablab.booking.service.utils.TimeUtils.DATE_REG_EXP;

@Data
public class RqRoomBookingDto {
    @NotBlank
    @Pattern(regexp = DATE_REG_EXP, message = "date should follow pattern: " + DATE_PATTERN)
    @ApiModelProperty(value = "time when booking starts, should follow pattern: " + DATE_PATTERN, example = "2020-04-20 10:00:00+0000")
    private String startBookingTime;
    @NotBlank
    @Pattern(regexp = DATE_REG_EXP, message = "date should follow pattern: " + DATE_PATTERN)
    @ApiModelProperty(value = "time when booking ends, should follow pattern: " + DATE_PATTERN, example = "2020-04-20 11:00:00+0000")
    private String endBookingTime;
    @NotNull
    @ApiModelProperty(value = "roomId", example = "10")
    private Long roomId;
    @NotNull
    @ApiModelProperty(value = "userId", example = "1")
    private Long userId;

}
