package use_cases.add_task;

/**
 * Input Boundary for actions which are related to adding tasks.
 */
public interface AddTaskInputBoundary {
    /**
     * Executes the add_task use case.
     * @param addTaskInputData the input data
     */
    void execute(AddTaskInputData addTaskInputData);
}
