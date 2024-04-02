package com.apollogix.managerskill.Util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

    private static final String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    public static String formatInstant(Instant instant) {
        return DateTimeFormatter.ofPattern(FORMAT_DATE_TIME)
                .withZone(ZoneId.systemDefault()).format(instant);
    }
}
