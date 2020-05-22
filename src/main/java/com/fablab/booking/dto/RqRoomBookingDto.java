package com.fablab.booking.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class RqRoomBookingDto {
    @ApiModelProperty(value = "startBookingTime", example = "2020-04-20 09:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date startBookingTime;
    @ApiModelProperty(value = "startBookingTime", example = "2020-04-20 10:30")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date endBookingTime;
    private Long roomId;
    @ApiModelProperty(value = "userId", example = "1")
    private Long userId;

}
