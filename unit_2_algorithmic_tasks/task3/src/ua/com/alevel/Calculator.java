package ua.com.alevel;

public class Calculator {

    private static final int SMALL_BREAK = 5;
    private static final int BIG_BREAK = 15;
    private static final int START_TIME_LESSONS = 540; // in minutes a.m
    private static final int LESSON = 45;

    public static int[] calculateLessonsFinishTime(int numberOfLessons) {
        int coefForBigBreak = --numberOfLessons / 2;
        int coefForSmallBreak = numberOfLessons - coefForBigBreak;
        numberOfLessons++;
        int endTimeLessons = START_TIME_LESSONS + coefForBigBreak * BIG_BREAK + coefForSmallBreak * SMALL_BREAK +
                numberOfLessons * LESSON;
        int[] dataAboutLessons = new int[3];
        dataAboutLessons[0] = numberOfLessons;
        dataAboutLessons[1] = START_TIME_LESSONS;
        dataAboutLessons[2] = endTimeLessons;
        return dataAboutLessons;
    }
}