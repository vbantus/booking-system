package com.fablab.booking.dto;

import lombok.Data;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;

@Data
public class RqCreateEventDto {
    private String title;
    private String description;
    private String location;
    private int participationFee;
    private String imagePath;
    private int entryCost;
    private Date startTime;
    private Date endTime;
    private Long userId;
}
