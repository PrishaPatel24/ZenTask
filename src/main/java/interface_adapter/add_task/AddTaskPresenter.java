package interface_adapter.add_task;

import interface_adapter.ViewModel;
import use_cases.add_task.AddTaskOutputBoundary;
import use_cases.add_task.AddTaskOutputData;

/**
 * The presenter for the add_task use case.
 */
public class AddTaskPresenter implements AddTaskOutputBoundary {
    private final TaskViewModel taskViewModel;

    public AddTaskPresenter(TaskViewModel taskViewModel) {
        this.taskViewModel = taskViewModel;
    }

    @Override
    public void prepareSuccessView(AddTaskOutputData response) {
        // On success, add task to the view.

        final TaskState taskState = taskViewModel.getState();
        taskState.setTask(response.getDescription());
        taskState.setError(null);
        this.taskViewModel.setState(taskState);
        taskViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final TaskState taskState = taskViewModel.getState();
        taskState.setError(error);
        taskViewModel.firePropertyChanged();
    }
}
