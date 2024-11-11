package use_cases.ai;

import javax.swing.*;

/**
 * The class that will implement the different functions of AI in the program.
 * It will generate a completed written version of the current note.
 */
public class AiInteractor implements AiInputBoundary {
    private final AiOutputBoundary aiOutputBoundary;

    // take in note panel in attribute or in parameter to the method, defining interfaces, etc
    // decide on functions for the calendar, load events, oauth 2 login, edit/modify events?

    public AiInteractor(AiInputBoundary aiInputBoundary, AiOutputBoundary aiOutputBoundary) {
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
     *
     * @param currNote The call to the AI to complete the current note.
     */
    public void generateResponse(String currNote) {
        // call to API here
        aiOutputBoundary.updateNote(currNote);
    }
}