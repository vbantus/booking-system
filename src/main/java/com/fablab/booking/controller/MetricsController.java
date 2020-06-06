package com.fablab.booking.controller;

import com.fablab.booking.dto.DashboardDto;
import com.fablab.booking.service.impl.MetricsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/booking/metrics")
public class MetricsController {

    private final MetricsServiceImpl metricsService;

    @GetMapping("/dashboard/{numberOfMonths}")
    public ResponseEntity<DashboardDto> getDashboard(@PathVariable("numberOfMonths") Integer numberOfMonths) {
        return ResponseEntity.ok(metricsService.createDashboard(numberOfMonths));
    }
}
