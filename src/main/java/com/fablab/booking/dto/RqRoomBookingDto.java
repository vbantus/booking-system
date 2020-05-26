package com.fablab.booking.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class RqRoomBookingDto {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ssZ")
    @ApiModelProperty(value = "time when booking starts, should follow pattern: yyyy-MM-dd HH:mm:ssZ", example =  "2020-04-20 10:00:00+0000")
    private Date startBookingTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ssZ")
    @ApiModelProperty(value = "time when booking ends, should follow pattern: yyyy-MM-dd HH:mm:ssZ", example =  "2020-04-20 11:00:00+0000")
    private Date endBookingTime;
    private Long roomId;
    @ApiModelProperty(value = "userId", example = "1")
    private Long userId;

}
