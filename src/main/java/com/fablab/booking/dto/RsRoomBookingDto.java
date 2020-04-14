package com.fablab.booking.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RsRoomBookingDto extends AbstractResponseEntityDto{
    private Date startBookingTime;
    private Date endBookingTime;
    private String status;
    private Long roomId;
    private Long userId;
}
