package use_cases.add_task;

/**
 * This class creates an instance object that is passes into the AddTaskInteractor.
 */
public class AddTaskInputData {
    private final String description;

    public AddTaskInputData(String description) {
        this.description = description;
    }

    String getDescription() {
        return description;
    }
}
