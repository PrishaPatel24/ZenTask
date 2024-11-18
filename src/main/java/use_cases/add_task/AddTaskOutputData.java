package use_cases.add_task;

/**
 * Output Data for the add_task Use Case.
 */
public class AddTaskOutputData {

    private final String description;
    private final boolean useCaseFailed;

    public AddTaskOutputData(String description, boolean useCaseFailed) {
        this.description = description;
        this.useCaseFailed = useCaseFailed;
    }

    public String getDescription() {
        return description;
    }

}