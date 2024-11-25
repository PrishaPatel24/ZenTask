package use_cases.ai;

import entity.Note;

/**
 * AiInputBoundary involved in the AI prompt use case.
 */
public interface AiInputBoundary {

    /**
     * Generates AI response.
     * @param note The modified prompt going into the AI."
     */
    void generateResponse(Note note);
}
