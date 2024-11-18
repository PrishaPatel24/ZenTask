package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a checklist.
 */
public class Checklist {
    private List<Task> tasks;

    public Checklist() {
        this.tasks = new ArrayList<>();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds new task to the checklist.
     * @param description the task description
     */
    public void addTask(String description) {
        this.tasks.add(new Task(description));
    }
}
