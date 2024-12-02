package use_cases.note;

import entity.Note;

/**
 * The "Use Case Interactor" for our two note-related use cases of refreshing
 * the contents of the note and saving the contents of the note. Since they
 * are closely related, we have combined them here for simplicity.
 */
public class NoteInteractor implements NoteInputBoundary {

    private final NoteOutputBoundary notePresenter;
    private final NoteDataAccessInterface inMemoryNoteDataAccessObject;

    public NoteInteractor(NoteOutputBoundary noteOutputBoundary, NoteDataAccessInterface inMemoryNoteDAO) {
        this.notePresenter = noteOutputBoundary;
        this.inMemoryNoteDataAccessObject = inMemoryNoteDAO;
    }

    @Override
    public void execute(NoteInputData noteInputData) {
        if (noteInputData.getTitle() == null || noteInputData.getTitle().isEmpty()) {
            notePresenter.prepareFailView("Note cannot be saved!");
        }
        else {
            if (!(inMemoryNoteDataAccessObject.getNotesSaved()).contains(noteInputData.getTitle())) {
                inMemoryNoteDataAccessObject.saveNote(noteInputData.getTitle(), noteInputData.getContent());
                final Note note = inMemoryNoteDataAccessObject.getNote(noteInputData.getTitle());
                final NoteOutputData noteOutputData = new NoteOutputData(note.getTitle(), note.getContent(), false);
                notePresenter.prepareSuccessView(noteOutputData);
            }
            else {
                notePresenter.prepareFailView("Note cannot be saved! Title: "
                        + noteInputData.getTitle() + "already exists!");
            }

            if (inMemoryNoteDataAccessObject.getNote(noteInputData.getTitle()) == null) {
                notePresenter.prepareFailView("Note did not save!");
            }
        }
    }
}

