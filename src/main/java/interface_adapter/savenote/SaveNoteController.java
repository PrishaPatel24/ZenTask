package interface_adapter.savenote;

import use_cases.savenote.SaveNoteInputBoundary;
import use_cases.savenote.SaveNoteInputData;

/**
 * Controller for our Note related Use Cases.
 */
public class SaveNoteController {

    private final SaveNoteInputBoundary noteInteractor;

    public SaveNoteController(SaveNoteInputBoundary noteInteractor) {
        this.noteInteractor = noteInteractor;
    }

    /**
     * Executes the Note related Use Cases.
     * @param content the content of the note being worked on
     * @param title the title of the note
     */
    public void execute(String content, String title) {
        final SaveNoteInputData newNote = new SaveNoteInputData(content, title);
        noteInteractor.execute(newNote);
    }

}
