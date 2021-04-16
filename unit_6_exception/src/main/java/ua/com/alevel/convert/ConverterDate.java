package ua.com.alevel.convert;

import ua.com.alevel.date.Date;
import ua.com.alevel.date.Month;

public class ConverterDate {

    private static final int[] ACCUMULATED_DAYS_IN_MONTH =
            { 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};
    private static final long MILLISECONDS_IN_YEAR = 31536000000L;
    private static final int MILLISECONDS_IN_DAY = 86400000;
    private static final int MILLISECONDS_IN_HOUR = 3600000;
    private static final int MILLISECONDS_IN_MINUTE = 60000;
    private static final int MILLISECONDS_IN_SECOND = 1000;

    public static Date convertMillisecondsToDate(long milliseconds) {
        Date date = new Date();
        date.setYear((int) (milliseconds / MILLISECONDS_IN_YEAR));
        milliseconds -= (date.getYear() * MILLISECONDS_IN_YEAR);
        long daysInMilliseconds = milliseconds / MILLISECONDS_IN_DAY;
        for (int i = 1; i < ACCUMULATED_DAYS_IN_MONTH.length; i++) {
            int temp = (int) (daysInMilliseconds - ACCUMULATED_DAYS_IN_MONTH[i]);
            if (temp < 0) {
                date.setMonth(Month.getMonthByValue(i));
                milliseconds -= ((long) ACCUMULATED_DAYS_IN_MONTH[i - 1] * MILLISECONDS_IN_DAY);
                i = ACCUMULATED_DAYS_IN_MONTH.length;
            } else if(temp > 0 && i == ACCUMULATED_DAYS_IN_MONTH.length - 1) {
                date.setMonth(Month.DECEMBER);
                milliseconds -= ((long) ACCUMULATED_DAYS_IN_MONTH[i] * MILLISECONDS_IN_DAY);
            }
        }
        date.setDay((int) (milliseconds / MILLISECONDS_IN_DAY));
        milliseconds -= ((long) date.getDay() * MILLISECONDS_IN_DAY);
        date.setHour((int) (milliseconds / MILLISECONDS_IN_HOUR));
        milliseconds -= ((long) date.getHour() * MILLISECONDS_IN_HOUR);
        date.setMinute((int) (milliseconds / MILLISECONDS_IN_MINUTE));
        milliseconds -= ((long) date.getMinute() * MILLISECONDS_IN_MINUTE);
        date.setSecond((int) (milliseconds / MILLISECONDS_IN_SECOND));
        milliseconds -= ((long) date.getSecond() * MILLISECONDS_IN_SECOND);
        date.setMillisecond((int) milliseconds);
        return date;
    }

    public static long convertToMilliseconds(Date date) {
        long milliseconds = 0;
        milliseconds += (date.getYear() * MILLISECONDS_IN_YEAR);
        milliseconds += ((long) ACCUMULATED_DAYS_IN_MONTH[date.getMonth().getValue() - 1] * MILLISECONDS_IN_DAY);
        int totalLeapYears = countLeapYears(date.getYear());
        if (isLeapYear(date.getYear()) && date.getMonth().getValue() > 2) {
            totalLeapYears++;
        }
        milliseconds += ((long) (date.getDay() + totalLeapYears) * MILLISECONDS_IN_DAY);
        milliseconds += ((long) date.getHour() * MILLISECONDS_IN_HOUR);
        milliseconds += ((long) date.getMinute() * MILLISECONDS_IN_MINUTE);
        milliseconds += ((long) date.getSecond() * MILLISECONDS_IN_SECOND);
        milliseconds += (date.getMillisecond());
        return milliseconds;
    }

    public static long convertToSeconds(Date date) {
        return convertToMilliseconds(date) / 1000;
    }

    public static long convertToMinutes(Date date) {
        return convertToSeconds(date) / 60;
    }

    public static long convertToHours(Date date) {
        return convertToMinutes(date) / 60;
    }

    private static boolean isLeapYear(int year) {
        if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) {
            return true;
        } else {
            return false;
        }
    }

    private static int countLeapYears(int year) {
        int count = 0;
        for (int i = 0; i < year; i++) {
            if (isLeapYear(year)) {
                count++;
            }
        }
        return count;
    }

}