package com.fablab.booking.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RsBookingSpaceRelationDto extends AbstractResponseEntityDto{
    private Date startBookingTime;
    private Date endBookingTime;
    private String status;
    private Long bookingSpaceId;
    private Long userId;
}
