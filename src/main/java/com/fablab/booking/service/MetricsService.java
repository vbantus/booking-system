package com.fablab.booking.service;

import com.fablab.booking.dto.DashboardDto;

public interface MetricsService {

    DashboardDto createDashboard(Integer numberOfMonths);
}
