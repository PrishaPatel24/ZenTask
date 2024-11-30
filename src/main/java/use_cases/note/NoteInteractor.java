package use_cases.note;

import data_access.InMemoryNoteDataAccessObject;
import entity.Note;

/**
 * The "Use Case Interactor" for our two note-related use cases of refreshing
 * the contents of the note and saving the contents of the note. Since they
 * are closely related, we have combined them here for simplicity.
 */
public class NoteInteractor implements NoteInputBoundary {

    private final NoteOutputBoundary notePresenter;
    private final InMemoryNoteDataAccessObject inMemoryNoteDataAccessObject;

    public NoteInteractor(NoteOutputBoundary noteOutputBoundary, InMemoryNoteDataAccessObject inMemoryNoteDAO) {
        this.notePresenter = noteOutputBoundary;
        this.inMemoryNoteDataAccessObject = inMemoryNoteDAO;
    }

    @Override
    public void execute(NoteInputData noteInputData) {
        final Note note = new Note(noteInputData.getContent(), noteInputData.getTitle());
        if (this.inMemoryNoteDataAccessObject.getNotes() != null) {
            for (String title : this.inMemoryNoteDataAccessObject.getNotes()) {
                if (noteInputData.getTitle().equals(title)) {
                    this.notePresenter.prepareFailView(
                            "Title already exists. Multiple notes cannot have the same title.");
                }
            }
        } // TODO: Check if this works
        this.inMemoryNoteDataAccessObject.saveNote(noteInputData.getContent(), note);
        final NoteOutputData noteOutputData = new NoteOutputData(note.getTitle(), false);
        this.notePresenter.prepareSuccessView(noteOutputData.getContent());
    }
}

