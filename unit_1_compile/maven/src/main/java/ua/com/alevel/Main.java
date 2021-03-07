package ua.com.alevel;

public class Main {

    public static void main(String[] args) {
        int[] array = new int[]{4,8,3,6,0,2};
        int[] cutArray = Calculator.removeMinElements(array);
        int lcm = Math.calculateLcm(cutArray);
        System.out.print("Numbers: ");
        for (int i = 0; i < cutArray.length; i++) {
            System.out.print(cutArray[i] + " ");
        }
        System.out.println();
        System.out.println("Least common multiple: " + lcm);
    }
}