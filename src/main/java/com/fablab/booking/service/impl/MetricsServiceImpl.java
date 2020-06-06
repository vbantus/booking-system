package com.fablab.booking.service.impl;

import com.fablab.booking.domain.AbstractEntity;
import com.fablab.booking.domain.BookingUser;
import com.fablab.booking.domain.RoomBooking;
import com.fablab.booking.dto.DashboardDto;
import com.fablab.booking.dto.MetricDto;
import com.fablab.booking.service.ArticleService;
import com.fablab.booking.service.EventService;
import com.fablab.booking.service.MetricsService;
import com.fablab.booking.service.RoomBookingService;
import com.fablab.booking.service.UserService;
import com.fablab.booking.service.utils.TimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MetricsServiceImpl implements MetricsService {

    private final ArticleService articleService;
    private final EventService eventService;
    private final UserService userService;
    private final RoomBookingService roomBookingService;

    @Override
    public DashboardDto createDashboard(Integer numberOfMonths) {

        LocalDate toTime = LocalDate.now();
        LocalDate fromTime = getFromTime(toTime, numberOfMonths);

        return DashboardDto.builder()
                .articles(articleService.count())
                .events(eventService.count())
                .bookings(roomBookingService.count())
                .metrics(createMetrics(fromTime, toTime))
                .build();
    }

    private List<MetricDto> createMetrics(LocalDate fromTime, LocalDate toTime) {
        List<MetricDto> metrics = new ArrayList<>();

        List<BookingUser> users = userService.findAllByCreateDateGreaterThan(TimeUtils.localDateToDate(fromTime));
        List<RoomBooking> bookings = roomBookingService
                .findAllByCreateDateGreaterThanEqual(TimeUtils.localDateToDate(fromTime));

        for (LocalDate localDate = fromTime; localDate.compareTo(toTime) <= 0; localDate = localDate.plusMonths(1)) {
            metrics.add(MetricDto.builder()
                    .date(localDate.format(DateTimeFormatter.ofPattern("yyyy-MMMM")))
                    .registeredUsers(fillMetricsWithData(localDate.getMonth(), localDate.getYear(), users))
                    .bookings(fillMetricsWithData(localDate.getMonth(), localDate.getYear(), bookings))
                    .userWhoBooked(fillMetricsWithRegisteredUsers(localDate.getMonth(), localDate.getYear(), bookings))
                    .build());
        }
        return metrics;
    }

    private Long fillMetricsWithData(Month month, Integer year, List<? extends AbstractEntity> entities) {
        return entities.stream()
                .filter(e -> {
                    LocalDate localDate = TimeUtils.dateToLocalDate(e.getCreateDate());
                    return localDate.getMonth().equals(month) && localDate.getYear() == year;
                })
                .count();
    }

    private Long fillMetricsWithRegisteredUsers(Month month, Integer year, List<RoomBooking> bookings) {
        return bookings.stream()
                .filter(e -> {
                    LocalDate localDate = TimeUtils.dateToLocalDate(e.getCreateDate());
                    return localDate.getMonth().equals(month) && localDate.getYear() == year;
                })
                .map(b -> b.getUser().getId())
                .distinct()
                .count();
    }

    private LocalDate getFromTime(LocalDate toDate, Integer numberOfMonths) {
        return LocalDate.of(toDate.getYear(), toDate.getMonth().getValue(), 1).minusMonths((numberOfMonths - 1));
    }

}
