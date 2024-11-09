package use_cases.add_task;

/**
 * The add_task Interactor.
 */
public class AddTaskInteractor implements AddTaskInputBoundary {
    private final AddTaskOutputBoundary addTaskPresenter;

    public AddTaskInteractor(AddTaskOutputBoundary addTaskOutputBoundary) {
        this.addTaskPresenter = addTaskOutputBoundary;
    }

    @Override
    public void execute(AddTaskInputData addTaskInputData) {
        final String description = addTaskInputData.getDescription();
        final AddTaskOutputData addTaskOutputData = new AddTaskOutputData(description, false);
        addTaskPresenter.prepareSuccessView(addTaskOutputData);
    }
}
