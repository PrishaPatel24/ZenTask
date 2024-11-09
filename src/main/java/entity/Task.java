package entity;

/**
 * The representation of a task for this program.
 */
public class Task {

    private final String description;
    private boolean checked;

    public Task(String description) {
        this.description = description;
        this.checked = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isChecked() {
        return checked;
    }

    /**
     * Marks the task as completed.
     */
    public void markChecked() {
        checked = true;
    }

}
