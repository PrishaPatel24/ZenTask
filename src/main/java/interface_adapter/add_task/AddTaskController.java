package interface_adapter.add_task;

import use_cases.add_task.AddTaskInputBoundary;
import use_cases.add_task.AddTaskInputData;

/**
 * The controller for the add_task Use Case.
 */
public class AddTaskController {
    private final AddTaskInputBoundary addTaskUseCaseInteractor;

    public AddTaskController(AddTaskInputBoundary addTaskUseCaseInteractor) {
        this.addTaskUseCaseInteractor = addTaskUseCaseInteractor;
    }

    /**
     * Executes the add_task Use Case.
     * @param description the task description
     */
    public void execute(String description) {
        final AddTaskInputData addTaskInputData = new AddTaskInputData(description);
        addTaskUseCaseInteractor.execute(addTaskInputData);
    }
}
