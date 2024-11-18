package use_cases.add_task;

import entity.Task;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class AddTaskInteractorTest {

    @Test
    public void successTest() {

        final AddTaskInputData inputData = new AddTaskInputData("test description");

        AddTaskOutputBoundary addTaskOB = new AddTaskOutputBoundary() {
            @Override
            public void prepareSuccessView(AddTaskOutputData outputData) {
                assertEquals("test description", outputData.getDescription());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
            }
        };
        AddTaskInteractor addTaskInteractor = new AddTaskInteractor(addTaskOB);
        addTaskInteractor.execute(inputData);
    }
}
