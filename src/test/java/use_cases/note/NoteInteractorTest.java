package use_cases.note;

import data_access.InMemoryNoteDataAccessObject;
import org.junit.Test;

import static org.junit.Assert.*;

public class NoteInteractorTest {

    @Test
    public void testExecuteSaveSuccess() {
        NoteInputData inputData = new NoteInputData("Test note", "This is a sample note! " +
                "This tests to see if the note is properly saved. ");
        NoteDataAccessInterface noteDataAccess = new InMemoryNoteDataAccessObject();
        noteDataAccess.saveNote(inputData.getTitle(), inputData.getContent());

        NoteOutputBoundary successPresenter = new NoteOutputBoundary() {
            @Override
            public void prepareSuccessView(String message) {
                assertEquals("Use case success is expected","This is a sample note! This tests to see if the note is properly saved. ", inputData.getContent());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
            }
        };

        NoteInputBoundary noteInteractor = new NoteInteractor(successPresenter, noteDataAccess);
        noteInteractor.execute(inputData);
    }
}