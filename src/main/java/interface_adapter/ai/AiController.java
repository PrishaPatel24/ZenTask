package interface_adapter.ai;

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
    public void generateResponse(String note) {
        aiInteractor.generateResponse(note + "Please briefly complete the note with a few sentences"
                + "based on the given notes.");
    }
}
