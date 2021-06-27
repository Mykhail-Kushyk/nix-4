package ua.com.alevel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class FileUpdater implements Runnable {

    private final State state;

    public FileUpdater(State state) {
        this.state = state;
    }

    @Override
    public void run() {
        update();
    }

    private void update() {
        File tempDirectory = new File(System.getProperty("user.dir"));
        File file = new File(tempDirectory.getAbsolutePath() + "\\unit_14_threads\\output.txt");
        try(FileWriter writer = new FileWriter(file, false)) {
            while (!state.getFinished()) {
                if (state.isUpdated()) {
                    writer.write(state.getCurrentStateMessage());
                    state.setUpdated(false);
                }
                if (state.getFinished()) break;
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}