package use_cases.add_task;

/**
 * The output boundary for the add_task Use Case.
 */
public interface AddTaskOutputBoundary {
    /**
     * Prepares the success view for the add_task Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(AddTaskOutputData outputData);

    /**
     * Prepares the failure view for the add_task Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
