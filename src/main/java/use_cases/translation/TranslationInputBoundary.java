package use_cases.translation;

/**
 * InputBoundary for translation use case.
 */
public interface TranslationInputBoundary {
    /**
     * Translates note.
     *
     * @param inputData contains text to be translated and selected language
     */
    void translateNote(TranslationInputData inputData);
}
