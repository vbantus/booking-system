package com.fablab.booking.dto;

import lombok.Data;

@Data
public class RqBookingSpaceDto {
    private String name;
    private int pricePerHour;
    private String description;
    private String imagePath;
}
