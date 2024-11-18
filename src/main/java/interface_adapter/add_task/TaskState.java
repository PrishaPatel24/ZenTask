package interface_adapter.add_task;

import entity.Task;

/**
 * The State for a task.
 */
public class TaskState {
    private Task task;
    private String error;

    public Task getTask() {
        return task;
    }

    public void setTask(String description) {
        this.task = new Task(description);
    }

    public void setError(String errorMessage) {
        this.error = errorMessage;
    }

    public String getError() {
        return error;
    }
}
