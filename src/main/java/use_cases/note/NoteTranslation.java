package use_cases.note;

import space.dynomake.libretranslate.Language;
import space.dynomake.libretranslate.Translator;

/**
 * Class for note translation.
 */
public class NoteTranslation {

    /**
     * Translates text from english to other language.
     * @param textToTranslate text that is to be translated
     */
    public static String translate(String textToTranslate) {
        final String res = Translator.translate(Language.ENGLISH, Language.RUSSIAN, textToTranslate);
        return res;
    }
}
