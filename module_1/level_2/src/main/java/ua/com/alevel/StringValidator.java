package ua.com.alevel;

import java.util.Stack;

public class StringValidator {

    public boolean isValidateGroups(String inputString) {
        if (inputString.isEmpty()) {
            return true;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < inputString.length(); i++) {
            char c = inputString.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (c == ')' || c == '}' || c == ']') {
                if (!stack.isEmpty()) {
                    char popped = stack.pop();
                    if ((c == ')' && popped != '(') || (c == '}' && popped != '{') || (c == ']' && popped != '[')) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}