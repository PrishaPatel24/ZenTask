package interface_adapter.translation;

import use_cases.translation.TranslationInputBoundary;
import use_cases.translation.TranslationInputData;

/**
 * Controller for translation use case.
 */
public class TranslationController {
    private final TranslationInputBoundary translationInteractor;

    public TranslationController(TranslationInputBoundary translationInteractor) {
        this.translationInteractor = translationInteractor;
    }

    /**
     * Handles the user clicking the translate button.
     * @param textToTranslate text to be translated
     * @param selectedLanguage language to translate to
     */
    public void translateNote(String textToTranslate, String selectedLanguage) {
        final TranslationInputData inputData = new TranslationInputData(textToTranslate, selectedLanguage);
        translationInteractor.translateNote(inputData);
    }
}
