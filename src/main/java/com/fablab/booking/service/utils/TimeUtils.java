package com.fablab.booking.service.utils;

import com.fablab.booking.domain.common.exception.TimeConflictException;

import java.util.Date;

public class TimeUtils {

    public static void validateDates(Date startTime, Date endTime) {
        if (startTime.after(endTime)) {
            throw new TimeConflictException("startTime: [" + startTime + "] has to be before endTime: [" + endTime + "]");
        }
    }
}
