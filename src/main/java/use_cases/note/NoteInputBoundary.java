package use_cases.note;

/**
 * The Input Boundary for our note-related use cases. Since they are closely related,
 * we have included them both in the same interface for simplicity.
 */
public interface NoteInputBoundary {

    /**
     * Executes the save note use case.
     * @param content the note being worked on
     * @param title the title of note
     */
    void executeSave(String content, String title);

    /**
     * Executes the upload note use case.
     * @param content the note being worked on
     */
    void executeUpload(String content);

    /**
     * Executes the clear note use case.
     * @param content the note being worked on
     */
    void executeClear(String content);

}
