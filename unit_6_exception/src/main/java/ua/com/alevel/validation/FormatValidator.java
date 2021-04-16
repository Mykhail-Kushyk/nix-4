package ua.com.alevel.validation;

import java.util.regex.Pattern;

public class FormatValidator {

    private String regex;
    private Pattern datePattern;

    public FormatValidator(String regex) {
        this.regex = regex;
        this.datePattern = Pattern.compile(regex);
    }

    public boolean matches(String date) {
        return datePattern.matcher(date).matches();
    }
}
