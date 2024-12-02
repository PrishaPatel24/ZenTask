package interface_adapter.add_task;

import java.util.ArrayList;
import java.util.List;

import entity.Task;

/**
 * The State for a task.
 */
public class TaskState {
    private List<Task> tasks;
    private String error;

    public TaskState() {
        tasks = new ArrayList<>();
    }

    public List<Task> getList() {
        return tasks;
    }

    /**
     * Adds new Task object to the list of tasks.
     * @param description of the task to be added
     */
    public void addTask(String description) {
        this.tasks.add(new Task(description));
    }

    public void setError(String errorMessage) {
        this.error = errorMessage;
    }

    public String getError() {
        return error;
    }
}
