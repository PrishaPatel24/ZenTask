package use_cases.note;

import junit.framework.TestCase;

public class NoteTranslationTest extends TestCase {

    public void testTranslate2Russian() {
        String text = "Hello world";
        String selectedLanguage = "Russian";
        NoteInputData inputData = new NoteInputData(text, selectedLanguage);

        NoteOutputData output = NoteTranslation.translate(inputData);
        String expectedOutput = "Привет мир";

        assertEquals(expectedOutput, output.getTranslatedText());
    }

    public void testTranslate2Spanish() {
        String text = "Welcome everyone";
        String selectedLanguage = "Spanish";
        NoteInputData inputData = new NoteInputData(text, selectedLanguage);

        NoteOutputData output = NoteTranslation.translate(inputData);
        String expectedOutput = "Bienvenido a todos";

        assertEquals(expectedOutput, output.getTranslatedText());
    }

    public void testTranslate2French() {
        String text = "Hello everyone";
        String selectedLanguage = "French";
        NoteInputData inputData = new NoteInputData(text, selectedLanguage);

        NoteOutputData output = NoteTranslation.translate(inputData);
        String expectedOutput = "Bonjour à tous";

        assertEquals(expectedOutput, output.getTranslatedText());
    }

    public void testTranslate2Arabic() {
        String text = "Please and thank you";
        String selectedLanguage = "Arabic";
        NoteInputData inputData = new NoteInputData(text, selectedLanguage);

        NoteOutputData output = NoteTranslation.translate(inputData);
        String expectedOutput = "من فضلك وشكرا لكم";

        assertEquals(expectedOutput, output.getTranslatedText());
    }

    public void testLanguageNotInDropdown() {
        // If selected language is not part of the dropdown list, should output original text
        String text = "Hello everyone";
        String selectedLanguage = "Italian";
        NoteInputData inputData = new NoteInputData(text, selectedLanguage);

        NoteOutputData output = NoteTranslation.translate(inputData);
        String expectedOutput = "Hello everyone";

        assertEquals(expectedOutput, output.getTranslatedText());
    }

    public void testTranslateEmptyString() {
        // Empty string should return empty string
        String text = " ";
        String selectedLanguage = "Russian";
        NoteInputData inputData = new NoteInputData(text, selectedLanguage);

        NoteOutputData output = NoteTranslation.translate(inputData);
        String expectedOutput = "";

        assertEquals(expectedOutput, output.getTranslatedText());
    }

    public void testExceededCharLimit() {
        String text = "hello ".repeat(400);
        String selectedLanguage = "Spanish";
        NoteInputData inputData = new NoteInputData(text, selectedLanguage);

        NoteOutputData output = NoteTranslation.translate(inputData);
        String expectedOutput = "Exceeded character limit";

        assertEquals(expectedOutput, output.getTranslatedText());
    }
}