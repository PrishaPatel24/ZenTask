package use_cases.note;

/**
 * The Note Output Data contains the translated text.
 */
public class TranslationOutputData {
    private final String translatedText;

    public TranslationOutputData(String translatedText) {
        this.translatedText = translatedText;
    }

    public String getTranslatedText() {
        return translatedText;
    }
}
