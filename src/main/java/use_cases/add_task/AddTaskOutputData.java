package use_cases.add_task;

import entity.Task;

/**
 * Output Data for the add_task Use Case.
 */
public class AddTaskOutputData {

    private final Task task;
    private final boolean useCaseFailed;

    public AddTaskOutputData(String description, boolean useCaseFailed) {
        this.task = new Task(description);
        this.useCaseFailed = useCaseFailed;
    }

    public String getDescription() {
        return task.getDescription();
    }

}
