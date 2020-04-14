package com.fablab.booking.dto;

import lombok.Data;

@Data
public class RqRoomDto {
    private String name;
    private int pricePerHour;
    private String description;
    private String imagePath;
}
