package com.example.StoreMonitor.Entities;

import java.time.Instant;
import java.time.ZoneId;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.sql.Timestamp;

public class TimeUtils {
    public static DayOfWeek getDayOfWeek(Timestamp timestamp) {
        // Convert the Timestamp to a ZonedDateTime using UTC time zone
        ZonedDateTime zonedDateTime = timestamp.toInstant().atZone(ZoneId.of("UTC"));

        // Get the DayOfWeek from the ZonedDateTime
        DayOfWeek dayOfWeek = zonedDateTime.getDayOfWeek();

        return dayOfWeek;
    }

    public static LocalTime getTimeOfDay(Timestamp timestamp) {
        // Convert the Timestamp to a ZonedDateTime using UTC time zone
        ZonedDateTime zonedDateTime = timestamp.toInstant().atZone(ZoneId.of("UTC"));

        // Get the LocalTime from the ZonedDateTime
        LocalTime timeOfDay = zonedDateTime.toLocalTime();

        return timeOfDay;
    }
}
