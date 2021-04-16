package ua.com.alevel.format;

public enum DateFormat {

    DEFAULT("dd/mm/yyyy",
            "(\\d{2}|0\\d)/(\\d{2}|0\\d)/\\d{1,4}",
            1),

    DEFAULT_IN_DASH("dd-mm-yyyy",
            "(\\d{2}|0\\d)-(\\d{2}|0\\d)-\\d{1,4}",
            2),

    DEFAULT_IN_DASH_WITH_TIME("dd-mm-yyyy HH:MM",
            "(\\d{2}|0\\d)-(\\d{2}|0\\d)-\\d{1,4} (\\d{2}|0\\d):(\\d{2}|0\\d)",
            3),

    SIMPLE_IN_SLASH("dd/mm/yy",
            "(\\d{2}|0\\d)/(\\d{2}|0\\d)/(\\d{2}|0\\d)",
            4),

    SIMPLE_IN_DASH("dd-mm-yy",
            "(\\d{2}|0\\d)-(\\d{2}|0\\d)-(\\d{2}|0\\d)",
            5),

    SIMPLE_IN_DASH_WITH_TIME("dd-mm-yy HH:MM",
            "(\\d{2}|0\\d)-(\\d{2}|0\\d)-(\\d{2}|0\\d) (\\d{2}|0\\d):(\\d{2}|0\\d)",
            6),

    SHORT_IN_SLASH("d/m/yy",
            "(\\d{1,2})/(\\d{1,2})/(\\d{2}|0\\d)",
            7),

    SHORT_IN_DASH("d-m-yy",
            "(\\d{1,2})-(\\d{1,2})-(\\d{2}|0\\d)",
            8),

    FULL("dd-mmm-yyyy",
            "(\\d{2}|0\\d)-[a-zA-Z]{3,9}-\\d{1,4}",
            9),

    FULL_WITH_TIME("dd-mmm-yyyy HH:MM",
            "(\\d{2}|0\\d)-[a-zA-Z]{3,9}-\\d{1,4} (\\d{2}|0\\d):(\\d{2}|0\\d)",
            10);

    private final String format;
    private final String regex;
    private final int number;

    public int getNumber() {
        return number;
    }

    public String getFormat() {
        return format;
    }

    public static DateFormat getFormatByNumber(int number) {
        for (DateFormat format: DateFormat.values()) {
            if (format.getNumber() == number) {
                return format;
            }
        }
        throw new IllegalArgumentException();
    }

    public String getRegex() {
        return regex;
    }

    DateFormat(String format, String regex, int number) {
        this.format = format;
        this.regex = regex;
        this.number = number;
    }
}