package use_cases.translation;

/**
 * OutputBoundary for translation use case.
 */
public interface TranslationOutputBoundary {
    /**
     * Presents the translated text.
     * @param outputData translated content
     */
    void updateTranslation(TranslationOutputData outputData);
}
