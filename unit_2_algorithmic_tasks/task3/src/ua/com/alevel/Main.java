package ua.com.alevel;

public class Main {

    public static void main(String[] args) {
        String formattedTime = Formatter.formatData(Calculator.calculateLessonsFinishTime(5));
        System.out.println(formattedTime);
    }
}