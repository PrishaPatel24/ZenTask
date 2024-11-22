package use_cases.ai;

import java.util.List;

import com.cohere.api.Cohere;
import com.cohere.api.requests.ChatRequest;
import com.cohere.api.types.ChatMessage;
import com.cohere.api.types.Message;
import com.cohere.api.types.NonStreamedChatResponse;

/**
 * This class handles the content in and out of the Cohere AI.
 */
public class AiRequest {
    /**
     * The method meant to polish user notes.
     * @param prompt The current notes.
     * @return The text result from the Cohere AI.
     */
    public String generateNotes(String prompt) {
        final String apiKey = "UBKSlAcJBhvwitbUnMrhGkutx3SkV2ZpSpMssN8h";
        final Cohere cohere = Cohere.builder().token(apiKey).clientName("snippet").build();

        final NonStreamedChatResponse response = cohere.chat(
                ChatRequest.builder()
                        .message("Improve these notes by editing for concision and "
                                + "adding more related content " + prompt)
                        .chatHistory(
                                List.of()).build());
        return response.getText();
    }
}
