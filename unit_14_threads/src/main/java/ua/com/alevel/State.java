package ua.com.alevel;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class State {

    private AtomicReference<String> currentStateMessage = new AtomicReference<>();
    private AtomicBoolean updated = new AtomicBoolean();

    public boolean getFinished() {
        return finished.get();
    }

    public void setFinished(boolean finished) {
        this.finished.set(finished);
    }

    private AtomicBoolean finished = new AtomicBoolean();

    public boolean isUpdated() {
        return updated.get();
    }

    public void setUpdated(boolean updated) {
        this.updated.set(updated);
    }

    public void setCurrentStateMessage(String currentStateMessage) {
        this.currentStateMessage.set(currentStateMessage);
    }

    public String getCurrentStateMessage() {
        return currentStateMessage.get();
    }
}