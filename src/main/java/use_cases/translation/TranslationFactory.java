package use_cases.translation;

import space.dynomake.libretranslate.Language;
import space.dynomake.libretranslate.Translator;

/**
 * Class for note translation.
 */
public class TranslationFactory {

    /**
     * Translates text from english to other language.
     *
     * @param inputData the input data containing the text to translate and the selected language
     * @return NoteOutputData containing the translation
     */
    public static TranslationOutputData translate(TranslationInputData inputData) {
        final int characterLimit = 2000;
        final String textToTranslate = inputData.getTextToTranslate();
        final String selectedLanguage = inputData.getSelectedLanguage();

        // checking if length of text exceeds char limit
        if (textToTranslate.length() > characterLimit) {
            return new TranslationOutputData("Exceeded character limit");
        }

        final String translation;
        switch (selectedLanguage) {
            case "Russian":
                translation = Translator.translate(Language.ENGLISH, Language.RUSSIAN, textToTranslate);
                break;
            case "French":
                translation = Translator.translate(Language.ENGLISH, Language.FRENCH, textToTranslate);
                break;
            case "Spanish":
                translation = Translator.translate(Language.ENGLISH, Language.SPANISH, textToTranslate);
                break;
            case "Arabic":
                translation = Translator.translate(Language.ENGLISH, Language.ARABIC, textToTranslate);
                break;
            default:
                translation = textToTranslate;
        }

        return new TranslationOutputData(translation);
    }
}
