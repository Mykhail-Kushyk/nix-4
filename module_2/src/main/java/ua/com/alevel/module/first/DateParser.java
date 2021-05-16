package ua.com.alevel.module.first;

public class DateParser {

    private final DateFormatValidator validator = new DateFormatValidator();

    public String parseDate(String date) {
        validator.setDatePattern("(\\d{2}|0\\d)/(\\d{2}|0\\d)/\\d{1,4}");
        if (validator.matches(date)) {
            String[] data = date.split("/");
            StringBuilder result = new StringBuilder();
            for (int i = data.length - 1; i >= 0; i--) {
                result.append(data[i]);
            }
            return result.toString();
        }

        validator.setDatePattern("(\\d{1,4})/(\\d{2}|0\\d)/(\\d{2}|0\\d)");
        if (validator.matches(date)) {
            String[] data = date.split("/");
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < data.length; i++) {
                result.append(data[i]);
            }
            return result.toString();
        }

        validator.setDatePattern("(\\d{2}|0\\d)-(\\d{2}|0\\d)-\\d{1,4}");
        if (validator.matches(date)) {
            String[] data = date.split("-");
            StringBuilder result = new StringBuilder();
            result.append(data[2]);
            result.append(data[0]);
            result.append(data[1]);
            return result.toString();
        }
        return "";
    }
}
