package ua.com.alevel.format;

import lombok.AllArgsConstructor;
import ua.com.alevel.date.Date;
import ua.com.alevel.date.Month;

@AllArgsConstructor
public class DateFormatter {

    private DateFormat format;

    public DateFormat getFormat() {
        return format;
    }

    public void setFormat(DateFormat format) {
        this.format = format;
    }

    public Date parse(String pattern) {
        if (pattern.contains("-") || pattern.contains(" ")) {
            pattern = pattern.replaceAll(" ", "/");
            pattern = pattern.replaceAll("-", "/");
            pattern = pattern.replaceAll(":", "/");

        }
        String[] dataOfDate = pattern.split("/");
        Date date = new Date();
        if (dataOfDate[1].length() > 2) {
            date.setMonth(Month.getMonthByName(dataOfDate[1]));
        } else {
            date.setMonth(Integer.parseInt(dataOfDate[1]));
        }
        date.setDay(Integer.parseInt(dataOfDate[0]));
        StringBuilder year = new StringBuilder();
        if (!format.getFormat().contains("yyyy")) {
            year.append(20).append(dataOfDate[2]);
        } else {
            year.append(dataOfDate[2]);
        }
        date.setYear(Integer.parseInt(year.toString()));
        if (dataOfDate.length > 3) {
            date.setHour(Integer.parseInt(dataOfDate[3]));
            date.setMinute(Integer.parseInt(dataOfDate[4]));
        }
        return date;
    }

    public String format(Date date) {
        String formattedDate = formatDay(format.getFormat(), date.getDay());
        formattedDate = formatMonth(formattedDate, date.getMonth());
        formattedDate = formatYear(formattedDate, date.getYear());
        formattedDate = formatTime(formattedDate, date.getHour(), date.getMinute());
        return formattedDate;
    }

    private String formatDay(String pattern, int day) {
        if (pattern.contains("dd")) {
            String stringDay = String.valueOf(day);
            if (stringDay.length() == 1) {
                stringDay = "0" + stringDay;
            }
            pattern = pattern.replaceAll("dd", stringDay);
        }
        if (pattern.contains("d")) {
            String stringDay = String.valueOf(day);
            pattern = pattern.replaceAll("d", stringDay);
        }
        return pattern;
    }

    private String formatMonth(String pattern, Month month) {
        if (pattern.contains("mmm")) {
            pattern = pattern.replaceAll("mmm", month.name());
        }
        if (pattern.contains("mm")) {
            String stringMonth = String.valueOf(month.getValue());
            if (stringMonth.length() == 1) {
                stringMonth = "0" + stringMonth;
            }
            pattern = pattern.replaceAll("mm", String.valueOf(stringMonth));
        }
        if (pattern.contains("m")) {
            pattern = pattern.replaceAll("m", String.valueOf(month.getValue()));
        }
        return pattern;
    }

    private String formatYear(String pattern, int year) {
        if (pattern.contains("yyyy")) {
            pattern = pattern.replaceAll("yyyy", String.valueOf(year));
        }
        if (pattern.contains("yy")) {
            String stringYear = String.valueOf(year);
            stringYear = stringYear.substring(stringYear.length() - 2);
            pattern = pattern.replaceAll("yy", stringYear);
        }
        return pattern;
    }

    private String formatTime(String pattern, int hour, int minute) {
        StringBuilder hourStr = new StringBuilder();
        StringBuilder minuteStr = new StringBuilder();
        if (pattern.contains("HH")) {
            if (hour < 10) {
                hourStr.append("0").append(hour);
            }
            else {
                hourStr.append(hour);
            }
            pattern = pattern.replaceAll("HH", hourStr.toString());
        }
        if (pattern.contains("MM")) {
            if (minute < 10) {
                minuteStr.append("0").append(minute);
            }
            else {
                minuteStr.append(minute);
            }
            pattern = pattern.replaceAll("MM", minuteStr.toString());
        }
        return pattern;
    }
}