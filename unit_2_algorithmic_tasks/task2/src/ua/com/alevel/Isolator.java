package ua.com.alevel;

public class Isolator {

    public static String isolateAllBesidesLetters(String string) {
        String outputArray = string.replaceAll("[^a-zA-Z_\\p{InCyrillic}]", "")
                .replaceAll("_", "");
        for (int i = 0; i < outputArray.length(); i++) {
            System.out.print(outputArray.charAt(i));
        }
        System.out.println();
        return outputArray;
    }
}