package com.fablab.booking.dto;

import lombok.Data;

import java.time.Duration;
import java.util.Date;

@Data
public class RsEventDto extends AbstractResponseEntityDto {
    private String title;
    private String description;
    private String location;
    private int participationFee;
    private String imagePath;
    private int entryCost;
    private Long eventId;
    private Date startTime;
    private Date endTime;
    private Long userId;
}
