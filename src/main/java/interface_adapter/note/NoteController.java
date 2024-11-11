package interface_adapter.note;

import use_cases.note.NoteInteractor;

/**
 * Controller for our Note related Use Cases.
 */
public class NoteController {

    private final NoteInteractor noteInteractor;

    public NoteController(NoteInteractor noteInteractor) {
        this.noteInteractor = noteInteractor;
    }

    /**
     * Executes the Note related Use Cases.
     * @param content the content of the note being worked on
     * @param title the title of the note
     */
    public void save(String content, String title) {
        noteInteractor.executeSave(content, title);
    }

    /**
     * Executes the Note related Use Cases.
     * @param content the content of the note being worked on
     */
    public void clear(String content) {
        noteInteractor.executeClear(content);
    }

    /**
     * Executes the Note related Use Cases.
     * @param content the content of the note being worked on
     */
    public void updateContent(String content) {
        noteInteractor.executeUpload(content);
    }

}
