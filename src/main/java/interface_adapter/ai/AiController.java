package interface_adapter.ai;

import use_cases.ai.AiInputBoundary;

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
        aiInteractor.generateResponse(note + "Please complete the note based on the"
                + "given notes.");
    }
}
