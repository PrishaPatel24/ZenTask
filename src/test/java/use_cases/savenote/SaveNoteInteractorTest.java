package use_cases.savenote;

import data_access.InMemorySaveNoteDataAccessObject;
import org.junit.Test;

import static org.junit.Assert.*;

public class SaveNoteInteractorTest {

    // this test checks to see that note is saving the note in-memory by calling execute(...) method of the Interactor.
    // inputData, a mock object, was made to ensure use case is successful.
    @Test
    public void testExecuteSaveSuccess() {
        SaveNoteInputData inputData = new SaveNoteInputData("Test note", "This is a sample note! " +
                "This tests to see if the note is properly saved. ");
        SaveNoteDataAccessInterface noteDataAccess = new InMemorySaveNoteDataAccessObject();

        SaveNoteOutputBoundary successPresenter = new SaveNoteOutputBoundary() {
            @Override
            public void prepareSuccessView(SaveNoteOutputData result) {
                assertEquals("This is a sample note! This tests to see if the note is properly saved. ", result.getContent());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("The use case is not expected to fail!");
            }
        };

        SaveNoteInputBoundary noteInteractor = new SaveNoteInteractor(successPresenter, noteDataAccess);
        noteInteractor.execute(inputData);
    }

    // this test checks to see that note is not saved if the note has no title added to it.
    // inputData, a mock object, was made to ensure use case failed.
    @Test
    public void testExecuteNoTitleFailure() {
        SaveNoteInputData inputData = new SaveNoteInputData("", "This is a sample note! " +
                "This tests to see if the note is properly saved. ");
        SaveNoteDataAccessInterface noteDataAccess = new InMemorySaveNoteDataAccessObject();

        SaveNoteOutputBoundary successPresenter = new SaveNoteOutputBoundary() {
            @Override
            public void prepareSuccessView(SaveNoteOutputData result) {
                fail("The use case is not expected to succeed!");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Note cannot be saved! No Title added!", errorMessage);
            }
        };

        SaveNoteInputBoundary noteInteractor = new SaveNoteInteractor(successPresenter, noteDataAccess);
        noteInteractor.execute(inputData);
    }

    // this test checks to see that note is not saved if another already saved note has the same title.
    // inputData1 and inputData2, mock objects, were made to ensure use case failed.
    @Test
    public void testExecuteSaveUniqueTitleFailure() {
        SaveNoteInputData inputData1 = new SaveNoteInputData("Test note", "This is a sample note! " +
                "This tests to see if the note is properly saved. ");
        SaveNoteInputData inputData2 = new SaveNoteInputData("Test note", " ");

        SaveNoteDataAccessInterface noteDataAccess = new InMemorySaveNoteDataAccessObject();
        noteDataAccess.saveNote(inputData1.getTitle(), inputData1.getContent());

        SaveNoteOutputBoundary failurePresenter = new SaveNoteOutputBoundary() {
            @Override
            public void prepareSuccessView(SaveNoteOutputData result) {
                fail("The use case is not expected to succeed!");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Note cannot be saved! Title: "
                        + inputData2.getTitle() + "already exists!", errorMessage);
            }
        };

        SaveNoteInputBoundary noteInteractor = new SaveNoteInteractor(failurePresenter, noteDataAccess);
        noteInteractor.execute(inputData2);

    }


    // This test ensures that the note is only saved by the Interactor and no other class does that.
    @Test
    public void testNoteNotSaved() {
        SaveNoteInputData saveNoteInputData = new SaveNoteInputData("Test note", "This is a sample note!");
        SaveNoteDataAccessInterface noteDataAccess = new InMemorySaveNoteDataAccessObject();

        assertNull(noteDataAccess.getNote(saveNoteInputData.getTitle()));
    }
}