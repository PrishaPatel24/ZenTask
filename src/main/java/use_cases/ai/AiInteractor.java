package use_cases.ai;

import entity.Note;

/**
 * The class that will implement the different functions of AI in the program.
 * It will generate a completed written version of the current note.
 */
public class AiInteractor implements AiInputBoundary {
    private final AiOutputBoundary aiOutputBoundary;
    private final AiRequest aiRequest;

    // take in note panel in attribute or in parameter to the method, defining interfaces, etc
    // decide on functions for the calendar, load events, oauth 2 login, edit/modify events?

    public AiInteractor(AiOutputBoundary aiOutputBoundary, AiRequest aiRequest) {
        this.aiOutputBoundary = aiOutputBoundary;
        this.aiRequest = aiRequest;
    }

    /**
     * Auto-complete the current notes written by the user with AI.
     *
     * @param currNote The call to the AI to complete the current note.
     */
    public void generateResponse(Note currNote) {
        final String newNoteContent = aiRequest.generateNotes(currNote.getContent());
        aiOutputBoundary.updateNote(new Note(newNoteContent, "NA"));
    }
}
