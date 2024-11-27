package entity;

/**
 * The representation of a task for this program.
 */
public class Task {

    private final String description;

    public Task(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
