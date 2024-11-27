package interface_adapter.add_task;

import java.util.ArrayList;
import java.util.List;

/**
 * The State for a task.
 */
public class TaskState {
    private List<String> tasks;
    private String error;

    public TaskState() {
        tasks = new ArrayList<>();
    }
    public List<String> getList() {
        return tasks;
    }

    public void addTask(String description) {
        this.tasks.add(description);
    }

    public void setError(String errorMessage) {
        this.error = errorMessage;
    }

    public String getError() {
        return error;
    }
}
