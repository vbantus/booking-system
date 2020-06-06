package com.fablab.booking.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MetricDto {
    private String date;
    private Long registeredUsers;
    private Long bookings;
    private Long userWhoBooked;
}
