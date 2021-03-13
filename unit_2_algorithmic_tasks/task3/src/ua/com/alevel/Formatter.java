package ua.com.alevel;

public class Formatter {

    public static String formatData(int[] dataAboutLessons) {
        StringBuilder result = new StringBuilder();
        result.append("Total lessons: " + dataAboutLessons[0] + System.lineSeparator());
        result.append("Start time of lessons: " + (dataAboutLessons[1] / 60) + ":" +
                (dataAboutLessons[1] % 60) + "0" + System.lineSeparator());
        result.append("Finish time of lessons: " + (dataAboutLessons[2] / 60) + ":" +
                (dataAboutLessons[2] % 60) + System.lineSeparator());
        return result.toString();
    }
}