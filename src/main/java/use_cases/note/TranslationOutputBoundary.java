package use_cases.note;

/**
 * OutputBoundary for translation use case.
 */
public interface TranslationOutputBoundary {
    /**
     * presents the translated text.
     * @param outputData translated content
     */
    void updateTranslation(TranslationOutputData outputData);
}
