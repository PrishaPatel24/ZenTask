package interface_adapter.translation;

import use_cases.translation.TranslationOutputBoundary;
import use_cases.translation.TranslationOutputData;
import view.NotesView;

/**
 * Presenter for translation use case.
 */
public class TranslationPresenter implements TranslationOutputBoundary {
    private final NotesView notesView;

    public TranslationPresenter(NotesView notesView) {
        this.notesView = notesView;
    }

    /**
     * Updates translation.
     * @param outputData translated content
     */
    public void updateTranslation(TranslationOutputData outputData) {
        final String translatedText = outputData.getTranslatedText();
        notesView.updateTranslation(translatedText);
    }
}
