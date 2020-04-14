package com.fablab.booking.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RqRoomBookingDto {
    private Date startBookingTime;
    private Date endBookingTime;
    private Long roomId;
    private Long userId;

}
