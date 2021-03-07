package ua.com.alevel;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

public class Calculator {

    public static int[] removeMinElements(int[] array) {
        int minElement = findMinElement(array);
        while (ArrayUtils.contains(array, minElement)) {
            array = ArrayUtils.removeElement(array, minElement);
        }
        Arrays.sort(array);
        int[] result = new int[]{array[0],array[1]};
        return result;
    }

    private static int findMinElement(int[] array) {
        int minElement = array[0];
        for (int j : array) {
            if (j < minElement) {
                minElement = j;
            }
        }
        return minElement;
    }
}