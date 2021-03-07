package ua.com.alevel;

import org.apache.commons.math3.util.ArithmeticUtils;

public class Math {

    public static int calculateLcm(int[] array) {
        return ArithmeticUtils.lcm(array[0], array[1]);
    }
}