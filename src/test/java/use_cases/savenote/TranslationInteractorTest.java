package use_cases.savenote;

import org.junit.Test;
import use_cases.translation.TranslationInputData;
import use_cases.translation.TranslationInteractor;
import use_cases.translation.TranslationOutputBoundary;
import use_cases.translation.TranslationOutputData;

import static org.junit.Assert.assertEquals;

public class TranslationInteractorTest {

    @Test
    public void testTranslate2Russian() {
        final TranslationInputData inputData = new TranslationInputData("Hello world", "Russian");

        TranslationOutputBoundary outputBoundary = new TranslationOutputBoundary() {
            @Override
            public void updateTranslation(TranslationOutputData outputData) {
                assertEquals("Привет мир", outputData.getTranslatedText());
            }
        };
        TranslationInteractor translationInteractor = new TranslationInteractor(outputBoundary);
        translationInteractor.translateNote(inputData);
    }

    @Test
    public void testTranslate2Spanish() {
        final TranslationInputData inputData = new TranslationInputData("Welcome everyone", "Spanish");

        TranslationOutputBoundary outputBoundary = new TranslationOutputBoundary() {
            @Override
            public void updateTranslation(TranslationOutputData outputData) {
                assertEquals("Bienvenido a todos", outputData.getTranslatedText());
            }
        };
        TranslationInteractor translationInteractor = new TranslationInteractor(outputBoundary);
        translationInteractor.translateNote(inputData);
    }

    @Test
    public void testTranslate2French() {
        final TranslationInputData inputData = new TranslationInputData("Hello everyone", "French");

        TranslationOutputBoundary outputBoundary = new TranslationOutputBoundary() {
            @Override
            public void updateTranslation(TranslationOutputData outputData) {
                assertEquals("Bonjour à tous", outputData.getTranslatedText());
            }
        };
        TranslationInteractor translationInteractor = new TranslationInteractor(outputBoundary);
        translationInteractor.translateNote(inputData);
    }

    @Test
    public void testTranslate2Arabic() {
        final TranslationInputData inputData = new TranslationInputData("Please and thank you", "Arabic");

        TranslationOutputBoundary outputBoundary = new TranslationOutputBoundary() {
            @Override
            public void updateTranslation(TranslationOutputData outputData) {
                assertEquals("من فضلك وشكرا لكم", outputData.getTranslatedText());
            }
        };
        TranslationInteractor translationInteractor = new TranslationInteractor(outputBoundary);
        translationInteractor.translateNote(inputData);
    }

    @Test
    public void testLanguageNotInDropdown() {
        // If selected language is not part of the dropdown list, should output original text
        final TranslationInputData inputData = new TranslationInputData("Hello everyone", "Italian");

        TranslationOutputBoundary outputBoundary = new TranslationOutputBoundary() {
            @Override
            public void updateTranslation(TranslationOutputData outputData) {
                assertEquals("Hello everyone", outputData.getTranslatedText());
            }
        };
        TranslationInteractor translationInteractor = new TranslationInteractor(outputBoundary);
        translationInteractor.translateNote(inputData);
    }

    @Test
    public void testTranslateEmptyString() {
        // Empty string should return empty string
        final TranslationInputData inputData = new TranslationInputData(" ", "French");

        TranslationOutputBoundary outputBoundary = new TranslationOutputBoundary() {
            @Override
            public void updateTranslation(TranslationOutputData outputData) {
                assertEquals("", outputData.getTranslatedText());
            }
        };
        TranslationInteractor translationInteractor = new TranslationInteractor(outputBoundary);
        translationInteractor.translateNote(inputData);
    }

    @Test
    public void testExceededCharLimit() {
        String text = "hello ".repeat(400);
        final TranslationInputData inputData = new TranslationInputData(text, "Spanish");

        TranslationOutputBoundary outputBoundary = new TranslationOutputBoundary() {
            @Override
            public void updateTranslation(TranslationOutputData outputData) {
                assertEquals("Exceeded character limit", outputData.getTranslatedText());
            }
        };
        TranslationInteractor translationInteractor = new TranslationInteractor(outputBoundary);
        translationInteractor.translateNote(inputData);
    }
}