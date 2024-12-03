package use_cases.translation;

/**
 * The Note Input Data contains the text to translate and the selected language to translate to.
 */
public class TranslationInputData {
    private final String textToTranslate;
    private final String selectedLanguage;

    public TranslationInputData(String textToTranslate, String selectedLanguage) {
        this.textToTranslate = textToTranslate;
        this.selectedLanguage = selectedLanguage;
    }

    public String getTextToTranslate() {
        return textToTranslate;
    }

    public String getSelectedLanguage() {
        return selectedLanguage;
    }

}
