package use_cases.savenote;

/**
 * The Input Boundary for our note-related use cases. Since they are closely related,
 * we have included them both in the same interface for simplicity.
 */
public interface SaveNoteInputBoundary {

    /**
     * Executes the save note use case.
     * @param saveNoteInputData the note being worked on.
     */
    void execute(SaveNoteInputData saveNoteInputData);

}
