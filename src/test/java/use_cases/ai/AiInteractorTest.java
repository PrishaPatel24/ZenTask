package use_cases.ai;

import entity.Note;
import org.junit.Test;
import use_cases.add_task.AddTaskInputData;
import use_cases.add_task.AddTaskInteractor;
import use_cases.add_task.AddTaskOutputBoundary;
import use_cases.add_task.AddTaskOutputData;

import static org.junit.Assert.*;

public class AiInteractorTest {

    @Test
    public void successTest() {
        AiOutputBoundary aiOutputBoundary = new AiOutputBoundary() {
            @Override
            public void updateNote(Note note) {
                assertNotNull(note);
            }
        };
        AiInteractor aiInteractor = new AiInteractor(aiOutputBoundary, new AiRequest());
        aiInteractor.generateResponse(new Note("testing Note!", "Note title"));
    }
}
