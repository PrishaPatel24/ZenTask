package use_cases.note;

/**
 * The Input Boundary for our note-related use cases. Since they are closely related,
 * we have included them both in the same interface for simplicity.
 */
public interface NoteInputBoundary {

    /**
     * Executes the save note use case.
     * @param noteInputData the note being worked on.
     */
    void execute(NoteInputData noteInputData);

}
