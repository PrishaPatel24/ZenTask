package use_cases.add_task;

public class AddTaskInputData {
    private final String description;

    public AddTaskInputData(String description) {
        this.description = description;
    }

    String getDescription() {
        return description;
    }
}
