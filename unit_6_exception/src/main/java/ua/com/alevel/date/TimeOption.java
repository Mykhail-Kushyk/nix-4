package ua.com.alevel.date;

public enum TimeOption {

    CENTURY(1),
    YEAR(2),
    HOUR(3),
    MINUTE(4),
    SECOND(5),
    MILLISECOND(6);

    private int number;

    public int getNumber() {
        return number;
    }

    TimeOption(int number) {
        this.number = number;
    }

    public static TimeOption getOptionByNumber(int number) {
        for (TimeOption option: TimeOption.values()) {
            if (option.number == number) {
                return option;
            }
        }
        throw new IllegalArgumentException();
    }
}