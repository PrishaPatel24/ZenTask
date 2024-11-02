package entity;

/**
 * The representation of a task for this program.
 */
public class Task {

    private final String description;
    private boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return done;
    }

    /**
     * Marks the task as completed.
     */
    public void markDone() {
        done = true;
    }

}
