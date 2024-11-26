package use_cases.ai;

import entity.Note;

/**
 * The output boundary for the AI use case.
 */
public interface AiOutputBoundary {
    /**
     * Add the AI generated completed note into the current note.
     * @param note The final output from the AI.
     */
    void updateNote(Note note);
}
