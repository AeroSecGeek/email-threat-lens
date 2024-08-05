package com.aerosecgeek.emailthreatlensservice.modules.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtils {
    private DateUtils(){}

    public static boolean isOlderThan24Hours(Date date) {
        Date now = new Date();
        long twentyFourHoursInMillis = TimeUnit.HOURS.toMillis(24);
        Date twentyFourHoursAgo = new Date(now.getTime() - twentyFourHoursInMillis);
        return date.before(twentyFourHoursAgo);
    }
}