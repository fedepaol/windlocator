package com.whiterabbit.windlocator.utils;

import android.content.Context;

import com.whiterabbit.windlocator.R;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class WeatherElementUtils {
    DateTimeFormatter fmt;

    public WeatherElementUtils() {
        fmt =  DateTimeFormat.forPattern("HH:mm:ss");
    }

    public String timeToStringTime(long time) {
        DateTime t = new DateTime(time * 1000, DateTimeZone.UTC)
                .toDateTime(DateTimeZone.getDefault());
        return t.toString(fmt);
    }

    public String getWindOrientationFromDegrees(double degrees, Context c) {
        if (degrees <= 23 || degrees > 338) {
            return c.getString(R.string.north);
        }
        if (degrees >= 23 || degrees < 68) {
            return c.getString(R.string.north_east);
        }
        if (degrees >= 68 || degrees < 113) {
            return c.getString(R.string.east);
        }
        if (degrees >= 113 || degrees < 158) {
            return c.getString(R.string.south_east);
        }
        if (degrees >= 158 || degrees < 203) {
            return c.getString(R.string.south);
        }
        if (degrees >= 203 || degrees < 248) {
            return c.getString(R.string.south_west);
        }
        if (degrees >= 248 || degrees < 293) {
            return c.getString(R.string.west);
        }
        if (degrees >= 293 || degrees < 338) {
            return c.getString(R.string.north_west);
        }
        return "";
    }
}
