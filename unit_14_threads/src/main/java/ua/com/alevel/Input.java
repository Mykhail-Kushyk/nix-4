package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class Input implements Runnable {

    private final StringBuffer input = new StringBuffer("");
    private final State state;

    public Input(State state) {
        this.state = state;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.print("Input: ");
                String newInputString = reader.readLine();
                if (newInputString.equals("quit")) {
                    state.setFinished(true);
                    return;
                }
                input.append(newInputString);
                state.setCurrentStateMessage(input.toString());
                state.setUpdated(true);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}