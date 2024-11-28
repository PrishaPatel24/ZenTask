package view;

import entity.Note;
import interface_adapter.ai.AiController;
import interface_adapter.translation.TranslationController;

import javax.swing.*;

/**
 * View for the Notes.
 */
public class NotesView extends JPanel {
    private final JTextArea notesTextArea;
    private final JPanel toolBarPanel;
    private final JButton aiButton;
    private final JButton languageButton;
    private final JTextArea outputArea;
    private AiController aiController;
    private TranslationController translationController;
    // add controllers and other view methods as necessary for language and note use cases

    public NotesView() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        final JPanel igeePanel = new JPanel();
        this.notesTextArea = new JTextArea();
        igeePanel.add(notesTextArea);
        this.add(notesTextArea);
        // @Igee the Notes text area is basically just a field to where you'd add your stuff.

        this.toolBarPanel = new JPanel();
        toolBarPanel.setLayout(new BoxLayout(toolBarPanel, BoxLayout.Y_AXIS));
        this.aiButton = new JButton("Complete Notes With AI");
        aiButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(aiButton)) {
                        aiController.generateResponse(new Note(notesTextArea.getText(), "NA"));
                    }
                }
        );
        toolBarPanel.add(aiButton);

        this.outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setSize(75, 50);

        // Dropdown menu
        final String[] languages = {"Russian", "French", "Spanish", "Arabic"};
        final JComboBox<String> languageDropdown = new JComboBox<>(languages);
        languageDropdown.setVisible(false);
        toolBarPanel.add(languageDropdown);

        // Language Translation Button
        this.languageButton = new JButton("Translate");
        languageButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(languageButton)) {
                        if (!languageDropdown.isVisible()) {
                            languageDropdown.setVisible(true);
                        }
                        else {
                            final String selectedLanguage = (String) languageDropdown.getSelectedItem();
                            final String textInput = notesTextArea.getText();
                            translationController.translateNote(textInput, selectedLanguage);
                            languageDropdown.setVisible(false);
                        }
                    }
                }
        );

        toolBarPanel.add(languageButton);
        toolBarPanel.add(outputArea);

        this.add(toolBarPanel);
    }

    /**
     * updates the translation.
     * @param translatedText text that has been translated
     */
    public void updateTranslation(String translatedText) {
        outputArea.setText(translatedText);
    }

    public void setTranslationController(TranslationController translationController) {
        this.translationController = translationController;
    }

    public void setAiController(AiController aiController) {
        this.aiController = aiController;
    }

    /**
     * Updates the display of the un-editable text area.
     * @param newNote The new note to display.
     */
    public void displayNewNote(Note newNote) {
        notesTextArea.setText(newNote.getContent());
    }
    // add methods for other controllers here
}
