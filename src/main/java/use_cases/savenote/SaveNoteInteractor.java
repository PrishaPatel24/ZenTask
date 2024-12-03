package use_cases.savenote;

import entity.Note;

/**
 * The "Use Case Interactor" for our two note-related use cases of refreshing
 * the contents of the note and saving the contents of the note. Since they
 * are closely related, we have combined them here for simplicity.
 */
public class SaveNoteInteractor implements SaveNoteInputBoundary {

    private final SaveNoteOutputBoundary notePresenter;
    private final SaveNoteDataAccessInterface inMemoryNoteDataAccessObject;

    public SaveNoteInteractor(SaveNoteOutputBoundary saveNoteOutputBoundary,
                              SaveNoteDataAccessInterface inMemoryNoteDAO) {
        this.notePresenter = saveNoteOutputBoundary;
        this.inMemoryNoteDataAccessObject = inMemoryNoteDAO;
    }

    @Override
    public void execute(SaveNoteInputData saveNoteInputData) {
        if (saveNoteInputData.getTitle().isEmpty()) {
            notePresenter.prepareFailView("Note cannot be saved! No Title added!");
        }
        else {
            if (!(inMemoryNoteDataAccessObject.getNotesSaved()).contains(saveNoteInputData.getTitle())) {
                inMemoryNoteDataAccessObject.saveNote(saveNoteInputData.getTitle(), saveNoteInputData.getContent());
                final Note note = inMemoryNoteDataAccessObject.getNote(saveNoteInputData.getTitle());
                final SaveNoteOutputData saveNoteOutputData =
                        new SaveNoteOutputData(note.getTitle(), note.getContent());
                notePresenter.prepareSuccessView(saveNoteOutputData);
            }
            else {
                notePresenter.prepareFailView("Note cannot be saved! Title: "
                        + saveNoteInputData.getTitle() + "already exists!");
            }

        }
    }
}

