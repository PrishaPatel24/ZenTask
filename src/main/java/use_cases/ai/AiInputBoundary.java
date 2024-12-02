package use_cases.ai;

/**
 * AiInputBoundary involved in the AI prompt use case.
 */
public interface AiInputBoundary {

    /**
     * Generates AI response.
     * @param note The modified prompt going into the AI."
     */
    void generateResponse(String note);
}
