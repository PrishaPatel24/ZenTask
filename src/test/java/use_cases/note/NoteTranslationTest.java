package use_case.note;

import junit.framework.TestCase;

public class NoteTranslationTest extends TestCase {

    public void testTranslate() {
        String text = "Hello world!";
        System.out.println(text);
        //--- translate it using Liza's class:
        String translated = NoteTranslation.translate(text);
        System.out.println(translated);
        assertEquals("Привет!", translated);
    }
}