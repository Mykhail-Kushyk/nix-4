package ua.com.alevel.date;

public enum Month {

    JANUARY (1, 31),
    FEBRUARY (2, 28),
    MARCH (3, 31),
    APRIL (4, 30),
    MAY (5, 31),
    JUNE (6, 30),
    JULY (7, 31),
    AUGUST (8, 31),
    SEPTEMBER (9, 30),
    OCTOBER (10, 31),
    NOVEMBER (11, 30),
    DECEMBER (12, 31);

    private final int number;
    private final int days;

    Month(int number, int days) {
        this.number = number;
        this.days = days;
    }

    public static Month getMonthByValue(int value) {
        Month result = Month.JANUARY;
        for (Month m: Month.values()) {
            if (m.getValue() == value) {
                result = m;
            }
        }
        return result;
    }

    public static Month getMonthByName(String strMonth) {
        for (Month m: Month.values()) {
            if (m.toString().equalsIgnoreCase(strMonth)) {
                return m;
            }
        }
        throw new IllegalArgumentException();
    }

    public int getValue() {
        return this.number;
    }

    public int getDays() {
        return this.days;
    }
}