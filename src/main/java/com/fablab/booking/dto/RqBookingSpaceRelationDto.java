package com.fablab.booking.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RqBookingSpaceRelationDto {
    private Date startBookingTime;
    private Date endBookingTime;
    private Long bookingSpaceId;
    private Long userId;

}
