package interface_adapter.ai;

import entity.Note;
import use_cases.ai.AiInputBoundary;

/**
 * The controller for the AI use case.
 */
public class AiController {
    private final AiInputBoundary aiInteractor;

    public AiController(AiInputBoundary aiInputBoundary) {
        this.aiInteractor = aiInputBoundary;
    }

    /**
     * Generates the AI response from a given note.
     * @param note The note in the JTextArea written by the user.
     */
    public void generateResponse(Note note) {
        aiInteractor.generateResponse(note);
    }
}
