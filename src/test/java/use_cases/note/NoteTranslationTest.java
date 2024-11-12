package use_cases.note;

import junit.framework.TestCase;

public class NoteTranslationTest extends TestCase {

    public void testTranslate() {
        // TODO: fix bug when "Bye guys" (w/o exclamation) fails to translate;
        String text = "Bye guys!";
        String translated = NoteTranslation.translate(text);
        assertEquals("Пока, ребята!", translated);
    }
}