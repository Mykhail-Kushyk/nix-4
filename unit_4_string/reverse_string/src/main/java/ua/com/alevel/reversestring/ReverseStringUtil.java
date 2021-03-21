package ua.com.alevel.reversestring;

public class ReverseStringUtil {

    public static String reverse(String inputSentence) {
        StringBuilder reversedSentence = new StringBuilder();
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < inputSentence.length(); i++) {
            if (!Character.isWhitespace(inputSentence.charAt(i))) {
                word.append(inputSentence.charAt(i));
                if (i == inputSentence.length() - 1) {
                    word.reverse();
                    reversedSentence.append(word.toString());
                }
            } else {
                String tempString = word.toString();
                word.delete(0, word.toString().length());
                word.append(reverseString(tempString));
                word.append(inputSentence.charAt(i));
                reversedSentence.append(word.toString());
                word.delete(0, word.toString().length());
            }
        }
        return reversedSentence.toString();
    }

    public static String reverse(String string, String dest) {
        return  string.replaceAll(dest, reverse(dest));
    }

    public static String reverse(String string, int firstIndex, int lastIndex) {
        String subString = string.substring(firstIndex, ++lastIndex);
        return string.replaceAll(subString, reverse(subString));
    }

    private static String reverseString(String string) {
        StringBuilder reversedString = new StringBuilder();
        for (int i = string.length() - 1; i >= 0; i--) {
            reversedString.append(string.charAt(i));
        }
        return reversedString.toString();
    }
}
