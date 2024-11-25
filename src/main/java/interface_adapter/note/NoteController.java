package interface_adapter.note;

import use_cases.note.NoteInputBoundary;
import use_cases.note.NoteInputData;

/**
 * Controller for our Note related Use Cases.
 */
public class NoteController {

    private final NoteInputBoundary noteInteractor;

    public NoteController(NoteInputBoundary noteInteractor) {
        this.noteInteractor = noteInteractor;
    }

    /**
     * Executes the Note related Use Cases.
     * @param content the content of the note being worked on
     * @param title the title of the note
     */
    public void execute(String content, String title) {
        final NoteInputData newNote = new NoteInputData(content, title);
        noteInteractor.execute(newNote);
    }

}
