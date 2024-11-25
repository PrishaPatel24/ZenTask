package use_cases.note;

/**
 * The Note Output Data contains the translated text.
 */
public class NoteOutputData {
    private final String translatedText;

    public NoteOutputData(String translatedText) {
        this.translatedText = translatedText;
    }

    public String getTranslatedText() {
        return translatedText;
    }
}
