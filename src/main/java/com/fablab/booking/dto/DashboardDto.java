package com.fablab.booking.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DashboardDto {
    private Long articles;
    private Long events;
    private Long bookings;
    private List<MetricDto> metrics;
}
