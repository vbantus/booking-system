package com.fablab.booking.dto;

import lombok.Data;

@Data
public class RsRoomDto extends AbstractResponseEntityDto {
    private String name;
    private int pricePerHour;
    private String description;
    private String imageUrl;
}
