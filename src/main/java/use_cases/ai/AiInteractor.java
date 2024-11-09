package use_cases.ai;

import javax.swing.*;

/**
 * The class that will implement the different functions of AI in the program.
 * It will generate a completed written version of the current note.
 */
public class AiInteractor {
    private final String key;
    private final AiInputBoundary aiInputBoundary;
    private final AiOutputBoundary aiOutputBoundary;

    // take in note panel in attribute or in parameter to the method, defining interfaces, etc
    // decide on functions for the calendar, load events, oauth 2 login, edit/modify events?

    AiInteractor(AiInputBoundary aiInputBoundary, AiOutputBoundary aiOutputBoundary) {
        this.key = "";
        this.aiInputBoundary = aiInputBoundary;
        this.aiOutputBoundary = aiOutputBoundary;
    }

    /**
     * Find the text content of a given note in the program.
     * @param textArea The field notes have been added to.
     * @return A string of the inputted text in notes.
     */
    public String getNote(JTextArea textArea) {
        return textArea.getText();
    }

    /**
     * Auto-complete the current notes written by the user with AI.
     * @param currNote The call to the AI to complete the current note.
     * @return The generated AI response.
     */
    public String generateResponse(String currNote) {
        // call to API here
        return "";
    }
}
