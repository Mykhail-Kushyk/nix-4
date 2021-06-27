package ua.com.alevel;

public class Main {

    public static void main(String[] args) {
        State state = new State();
        Thread input = new Thread(new Input(state));
        Thread updater = new Thread(new FileUpdater(state));
        input.start();
        updater.start();
    }
}