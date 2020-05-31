package com.fablab.booking.service.utils;

import com.fablab.booking.exception.TimeConflictException;

import java.util.Date;

public class TimeUtils {

    public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ssZ";
    public static final String DATE_REG_EXP = "^[1-9][0-9]{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1]) (2[0-3]|[01][0-9]):[0-5][0-9]:[0-5][0-9](([\\\\+])|([\\\\-]))0{0,2}[0-1][0-9]$";

    public static void validateDates(Date startTime, Date endTime) {
        if (startTime.after(endTime)) {
            throw new TimeConflictException("startTime: [" + startTime + "] has to be before endTime: [" + endTime + "]");
        }
    }
}
