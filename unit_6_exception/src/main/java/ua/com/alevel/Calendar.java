package ua.com.alevel;

import ua.com.alevel.convert.ConverterDate;
import ua.com.alevel.date.Date;
import ua.com.alevel.date.TimeOption;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static ua.com.alevel.convert.ConverterDate.*;
import static ua.com.alevel.convert.ConverterDate.convertToMilliseconds;

public class Calendar {

    public long getDifferenceIn(TimeOption option, Date firstDate, Date secondDate) {
        if (Objects.isNull(option) || Objects.isNull(firstDate) || Objects.isNull(secondDate)) {
            throw new NullPointerException("Fields can not be empty");
        }
        switch (option) {
            case CENTURY: {
                return Math.abs((firstDate.getYear() / 100) - (secondDate.getYear() / 100));
            }
            case YEAR: {
                return Math.abs(firstDate.getYear() - secondDate.getYear());
            }
            case HOUR:
                return Math.abs(convertToHours(firstDate) - convertToHours(secondDate));
            case MINUTE:
                return Math.abs(convertToMinutes(firstDate) - convertToMinutes(secondDate));
            case SECOND:
                return Math.abs(convertToSeconds(firstDate) - convertToSeconds(secondDate));
            case MILLISECOND:
                return Math.abs(convertToMilliseconds(firstDate) - convertToMilliseconds(secondDate));
        }
        return 0;
    }

    public List<Date> sortDescending(List<Date> dates) {
        dates.sort(Comparator.comparing(ConverterDate::convertToMilliseconds));
        return dates;
    }

    public List<Date> sortAscending(List<Date> dates) {
        dates.sort(Comparator.comparing(ConverterDate::convertToMilliseconds).reversed());
        return dates;
    }
}