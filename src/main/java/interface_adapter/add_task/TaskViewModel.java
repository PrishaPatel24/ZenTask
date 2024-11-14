package interface_adapter.add_task;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the TaskView.
 */
public class TaskViewModel extends ViewModel<TaskState> {
    public TaskViewModel() {
        super("task");
        setState(new TaskState());
    }
}