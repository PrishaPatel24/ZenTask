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

        NoteOutputBoundary successPresenter = new NoteOutputBoundary() {
            @Override
            public void prepareSuccessView(NoteOutputData result) {
                assertEquals("This is a sample note! This tests to see if the note is properly saved. ", result.getContent());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("The use case is not expected to fail!");
            }
        };

        NoteInputBoundary noteInteractor = new NoteInteractor(successPresenter, noteDataAccess);
        noteInteractor.execute(inputData);
    }

    @Test
    public void testExecuteSaveUniqueTitleFailure() {
        NoteInputData inputData1 = new NoteInputData("Test note", "This is a sample note! " +
                "This tests to see if the note is properly saved. ");
        NoteInputData inputData2 = new NoteInputData("Test note", " ");

        NoteDataAccessInterface noteDataAccess = new InMemoryNoteDataAccessObject();
        noteDataAccess.saveNote(inputData1.getTitle(), inputData1.getContent());

        NoteOutputBoundary failurePresenter = new NoteOutputBoundary() {
            @Override
            public void prepareSuccessView(NoteOutputData result) {
                fail("The use case is not expected to succeed!");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Note cannot be saved!", errorMessage);
            }
        };

        NoteInputBoundary noteInteractor = new NoteInteractor(failurePresenter, noteDataAccess);
        noteInteractor.execute(inputData2);

    }

    @Test
    public void testNoteNotSaved() { // This test ensures that the note is only saved by the Interactor and no other class does that.
        NoteInputData noteInputData = new NoteInputData("Test note", "This is a sample note!");
        NoteDataAccessInterface noteDataAccess = new InMemoryNoteDataAccessObject();

        assertNull(noteDataAccess.getNote(noteInputData.getTitle()));
    }
}