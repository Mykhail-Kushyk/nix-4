package ua.com.alevel;

public class ApplicationSecondLevel {

    public void run() {
        StringValidator validator = new StringValidator();
        String input = "{text)  (abc) [";
        System.out.println("Input string: " + input);
        if (validator.isValidateGroups(input)) {
            System.out.println("String is valid");
        } else {
            System.out.println("String is invalid");
        }
    }
}