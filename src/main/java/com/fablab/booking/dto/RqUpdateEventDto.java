package com.fablab.booking.dto;

import lombok.Data;

import java.time.Duration;
import java.util.Date;

@Data
public class RqUpdateEventDto {
    private String title;
    private String description;
    private String location;
    private int participationFee;
    private String imagePath;
    private int entryCost;
    private Date startTime;
    private Date endTime;
}
