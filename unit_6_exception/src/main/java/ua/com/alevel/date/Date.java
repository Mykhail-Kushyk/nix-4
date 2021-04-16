package ua.com.alevel.date;

import lombok.*;
import ua.com.alevel.convert.ConverterDate;
import ua.com.alevel.format.DateFormat;
import ua.com.alevel.format.DateFormatter;
import ua.com.alevel.validation.FormatValidator;

import java.util.Objects;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Date {

    private int year;
    private Month month;
    private int day;
    private int hour;
    private int minute;
    private int second;
    private int millisecond;
    private DateFormatter formatter = new DateFormatter(DateFormat.DEFAULT);
    private static final long MILLISECONDS_IN_YEAR = 31536000000L;
    private static final int MILLISECONDS_IN_HOUR = 3600000;
    private static final int MILLISECONDS_IN_MINUTE = 60000;
    private static final int MILLISECONDS_IN_SECOND = 1000;

    public Date(String date, DateFormatter formatter) {
        FormatValidator validator = new FormatValidator(formatter.getFormat().getRegex());
        if (!validator.matches(date)) {
            throw new IllegalArgumentException("Invalid format!");
        }
        reset(formatter.parse(date));
        this.formatter = formatter;
    }

    private void reset(Date date) {
        this.year = date.getYear();
        this.month = date.getMonth();
        this.day = date.getDay();
        this.hour = date.getHour();
        this.minute = date.getMinute();
        this.second = date.getSecond();
        this.millisecond = date.getMillisecond();
    }

    public String getFormattedDate() {
        return formatter.format(this);
    }

    public void setYear(int year) {
        if (year < 0 || year > 3000) {
            throw new IllegalArgumentException("Field year should be from 0 to 3000");
        }
        this.year = year;
    }

    public void setMillisecond(int millisecond) {
        if (millisecond < 0 || millisecond > 999) {
            throw new IllegalArgumentException("Field millisecond should be from 0 to 999");
        }
        this.millisecond = millisecond;
    }

    public void setSecond(int second) {
        if (second < 0 || second > 59) {
            throw new IllegalArgumentException("Field second should be from 0 to 59");
        }
        this.second = second;
    }

    public void setMinute(int minute) {
        if (minute < 0 || minute > 59) {
            throw new IllegalArgumentException("Field minute should be from 0 to 59");
        }
        this.minute = minute;
    }

    public void setHour(int hour) {
        if (hour < 0 || hour > 23) {
            throw new IllegalArgumentException("Field hour should be from 1 to 23");
        }
        this.hour = hour;
    }

    public void setDay(int day) {
        int daysInMonth = this.getMonth().getDays();
        if (this.getMonth() == Month.FEBRUARY) {
            if ((this.getYear() % 400 == 0) || (this.getYear() % 4 == 0) && (this.getYear() % 100 != 0)) {
                daysInMonth++;
            }
        }
        if (day < 1 || day > daysInMonth) {
            throw new IllegalArgumentException("Field day should be from 1 to " + daysInMonth);
        }
        this.day = day;
    }

    public void setMonth(Month month) {
        String strMonth = month.toString().toLowerCase();
        boolean isValid = false;
        for (Month m: Month.values()) {
            if (m.toString().toLowerCase().equals(strMonth)) {
                isValid = true;
            }
        }
        if (!isValid) {
            throw new IllegalArgumentException("Month is invalid");
        }
        this.month = month;
    }

    public void setMonth(int numberMonth) {
        if (numberMonth < 1 || numberMonth > 12) {
            throw new IllegalArgumentException("Field month should be from 1 to 12");
        }
        this.month = Month.getMonthByValue(numberMonth);
    }

    public void subtract(long subtracted, TimeOption option) {
        if (Objects.isNull(option)) {
            throw new NullPointerException("Option is empty");
        }
        switch (option) {
            case CENTURY: {
                long milliseconds = ConverterDate.convertToMilliseconds(this);
                milliseconds -= MILLISECONDS_IN_YEAR * 100 * subtracted;
                reset(ConverterDate.convertMillisecondsToDate(milliseconds));
                break;
            }
            case YEAR: {
                long milliseconds = ConverterDate.convertToMilliseconds(this);
                milliseconds -= MILLISECONDS_IN_YEAR * subtracted;
                reset(ConverterDate.convertMillisecondsToDate(milliseconds));
                break;
            }
            case HOUR: {
                long milliseconds = ConverterDate.convertToMilliseconds(this);
                milliseconds -= MILLISECONDS_IN_HOUR * subtracted;
                reset(ConverterDate.convertMillisecondsToDate(milliseconds));
                break;
            }
            case MINUTE: {
                long milliseconds = ConverterDate.convertToMilliseconds(this);
                milliseconds -= MILLISECONDS_IN_MINUTE * subtracted;
                reset(ConverterDate.convertMillisecondsToDate(milliseconds));
                break;
            }
            case SECOND: {
                long milliseconds = ConverterDate.convertToMilliseconds(this);
                milliseconds -= MILLISECONDS_IN_SECOND * subtracted;
                reset(ConverterDate.convertMillisecondsToDate(milliseconds));
                break;
            }
            case MILLISECOND: {
                long milliseconds = ConverterDate.convertToMilliseconds(this);
                milliseconds -= subtracted;
                reset(ConverterDate.convertMillisecondsToDate(milliseconds));
                break;
            }
        }
    }

    public void add(long addend, TimeOption option) {
        if (Objects.isNull(option)) {
            throw new NullPointerException("Option is empty");
        }
        switch (option) {
            case CENTURY: {
                long milliseconds = ConverterDate.convertToMilliseconds(this);
                milliseconds += MILLISECONDS_IN_YEAR * 100 * addend;
                reset(ConverterDate.convertMillisecondsToDate(milliseconds));
                break;
            }
            case YEAR: {
                long milliseconds = ConverterDate.convertToMilliseconds(this);
                milliseconds += MILLISECONDS_IN_YEAR * addend;
                reset(ConverterDate.convertMillisecondsToDate(milliseconds));
                break;
            }
            case HOUR: {
                long milliseconds = ConverterDate.convertToMilliseconds(this);
                milliseconds += MILLISECONDS_IN_HOUR * addend;
                reset(ConverterDate.convertMillisecondsToDate(milliseconds));
                break;
            }
            case MINUTE: {
                long milliseconds = ConverterDate.convertToMilliseconds(this);
                milliseconds += MILLISECONDS_IN_MINUTE * addend;
                reset(ConverterDate.convertMillisecondsToDate(milliseconds));
                break;
            }
            case SECOND: {
                long milliseconds = ConverterDate.convertToMilliseconds(this);
                milliseconds += MILLISECONDS_IN_SECOND * addend;
                reset(ConverterDate.convertMillisecondsToDate(milliseconds));
                break;
            }
            case MILLISECOND: {
                long milliseconds = ConverterDate.convertToMilliseconds(this);
                milliseconds += addend;
                reset(ConverterDate.convertMillisecondsToDate(milliseconds));
                break;
            }
        }
    }
}
