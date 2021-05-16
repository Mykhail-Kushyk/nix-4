package ua.com.alevel.module.first;

import java.util.regex.Pattern;

public class DateFormatValidator {

    private String regex;
    private Pattern datePattern;

    public void setDatePattern(String regex) {
        this.regex = regex;
        this.datePattern = Pattern.compile(regex);
    }

    public boolean matches(String date) {
        return datePattern.matcher(date).matches();
    }
}
