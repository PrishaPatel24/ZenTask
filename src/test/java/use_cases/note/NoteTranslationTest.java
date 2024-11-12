package use_cases.note;

import junit.framework.TestCase;

public class NoteTranslationTest extends TestCase {

    public void testTranslate() {
        String text = "Hello world!";
        String translated = NoteTranslation.translate(text);
        assertEquals("Привет!", translated);
    }
}