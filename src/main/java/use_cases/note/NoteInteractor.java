package use_cases.note;

import data_access.InMemoryNoteDataAccessObject;
import entity.Note;
import org.checkerframework.checker.units.qual.N;

/**
 * The "Use Case Interactor" for our two note-related use cases of refreshing
 * the contents of the note and saving the contents of the note. Since they
 * are closely related, we have combined them here for simplicity.
 */
public class NoteInteractor implements NoteInputBoundary {

    private final NoteOutputBoundary notePresenter;
    private final InMemoryNoteDataAccessObject inMemoryNoteDataAccessObject;

    public NoteInteractor(NoteOutputBoundary noteOutputBoundary, InMemoryNoteDataAccessObject inMemoryNoteDataAccessObject) {
        this.notePresenter = noteOutputBoundary;
        this.inMemoryNoteDataAccessObject = inMemoryNoteDataAccessObject;
    }

    @Override
    public void executeSave(String content, String title) {
        final Note noteInputData = new Note(content, title);
        inMemoryNoteDataAccessObject.saveNote(content, (Note) noteInputData);
        this.notePresenter.prepareSuccessView(content);
    }

    @Override
    public void executeUpload(String content) {
        if (content == null || content.isEmpty()) {
            this.notePresenter.prepareFailView("Upload failed");
        }
        this.notePresenter.prepareSuccessView(content);
    }

    @Override
    public void executeClear(String content) {
        this.notePresenter.prepareSuccessView(" ");
    }
}
