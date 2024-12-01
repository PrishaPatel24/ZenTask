package use_cases.note;

/**
 * Interactor for translation use case.
 */
public class TranslationInteractor implements TranslationInputBoundary {
    private final TranslationOutputBoundary outputBoundary;

    public TranslationInteractor(TranslationOutputBoundary outputBoundary) {

        this.outputBoundary = outputBoundary;
    }

    /**
     * Translates input data.
     *
     * @param inputData contains text to be translated and selected language
     */
    public void translateNote(TranslationInputData inputData) {
        final TranslationOutputData outputData = TranslationFactory.translate(inputData);
        outputBoundary.updateTranslation(outputData);
    }
}
