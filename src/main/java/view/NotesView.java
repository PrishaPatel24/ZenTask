package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import org.jetbrains.annotations.NotNull;

import interface_adapter.ai.AiController;
import interface_adapter.note.NoteController;
import interface_adapter.note.NoteState;
import interface_adapter.note.NoteViewModel;
import interface_adapter.translation.TranslationController;

/**
 * This class sets up the view of the Notes Use Case.
 */
public class NotesView extends JPanel implements ActionListener, PropertyChangeListener {
    static final int DIVIDER = 400;

    private final NoteViewModel noteViewModel;
    private TranslationController translationController;
    private AiController aiController;
    private NoteController noteController;

    private JLabel noteName;
    private JTextArea noteInputField;
    private JTextArea outputArea;

    private JButton saveNoteButton;
    private JButton uploadButton;
    private JButton clearButton;
    private JButton deleteButton;

    private JPanel notePanel;
    private JPanel editPanel;
    private JPanel savePanel;

    public NotesView(NoteViewModel noteViewModel) {
        this.noteViewModel = noteViewModel;
        this.noteViewModel.addPropertyChangeListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setUpUi();

    }

    private void setUpUi() {
        initializeComponents();
        setUpActionListeners();
        buildUi();
    }

    private void initializeComponents() {
        notePanel = new JPanel();
        editPanel = new JPanel();
        savePanel = new JPanel();

        noteName = new JLabel("New Note");

        noteInputField = new JTextArea();

        saveNoteButton = new JButton("Save Note");
        uploadButton = new JButton("Upload");
        clearButton = new JButton("Clear");
        deleteButton = new JButton("Delete");

    }

    private void setUpActionListeners() {
        saveNoteButton.addActionListener(this::saveNote);
        uploadButton.addActionListener(this::uploadText);
        clearButton.addActionListener(this::clearText);
        deleteButton.addActionListener(this::deleteNote);

    }

    private void saveNote(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(saveNoteButton)) {
            final String newNoteName = JOptionPane.showInputDialog("Enter new note name");
            noteName.setText(newNoteName);
            noteController.execute(noteName.getText(), noteInputField.getText());
            if (newNoteName != null) {
                final JOptionPane optionPane = new JOptionPane();
                optionPane.showMessageDialog(null, "Note saved successfully");
            }
        }

    }

    private void uploadText(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(uploadButton)) {
            final JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Upload");
            final int returnVal = fileChooser.showOpenDialog(this);
            final File selectedFile = fileChooser.getSelectedFile();
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                    noteInputField.setText("");
                    String line;
                    while ((line = reader.readLine()) != null) {
                        noteInputField.append(line);
                        noteInputField.append("\n");
                    }
                }
                catch (IOException exception) {
                    JOptionPane.showMessageDialog(this, exception.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        }

    }

    private void clearText(ActionEvent evt) {
        if (evt.getSource().equals(clearButton)) {
            noteController.execute(noteInputField.getText(), noteName.getText());
        }

    }

    private void deleteNote(ActionEvent evt) {
        if (evt.getSource().equals(deleteButton)) {
            noteController.execute(noteInputField.getText(), noteName.getText());
            noteName.setText("New Note");
        }

    }

    private void buildUi() {
        notePanel.setLayout(new BoxLayout(notePanel, BoxLayout.Y_AXIS));

        noteName.setAlignmentX(Component.CENTER_ALIGNMENT);
        notePanel.add(noteName);

        noteInputField.setLineWrap(true);
        noteInputField.setWrapStyleWord(true);
        notePanel.add(noteInputField);

        editPanel.add(uploadButton);
        editPanel.add(clearButton);
        editPanel.add(deleteButton);
        notePanel.add(editPanel);

        savePanel.add(saveNoteButton);
        notePanel.add(savePanel);

        final JPanel functionalityPanel = getjPanel();

        final JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, notePanel, functionalityPanel);
        splitPane.setDividerLocation(DIVIDER);

        this.add(splitPane);

    }

    @NotNull
    private JPanel getjPanel() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        final JPanel tools = new JPanel();

        final JButton aiButton = new JButton("Complete Notes With AI");
        aiButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(aiButton)) {
                        aiController.generateResponse(noteInputField.getText());
                        // Jenna, you may need to call some method since you cannot create an instance of Note here.

                    }
                }
        );
        tools.add(aiButton);

        // Dropdown menu
        final String[] languages = {"Russian", "French", "Spanish", "Arabic"};
        final JComboBox<String> languageDropdown = new JComboBox<>(languages);
        languageDropdown.setVisible(false);
        tools.add(languageDropdown);
  
        final JButton languageButton = new JButton("Translate");
        languageButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(languageButton)) {
                        if (!languageDropdown.isVisible()) {
                            languageDropdown.setVisible(true);
                        }
                        else {
                            final String selectedLanguage = (String) languageDropdown.getSelectedItem();
                            final String textInput = outputArea.getText();
                            translationController.translateNote(textInput, selectedLanguage);
                            languageDropdown.setVisible(false);
                        }
                    }
                }
        );
        tools.add(languageButton);

        outputArea = new JTextArea();
        outputArea.setEditable(false);

        panel.add(outputArea);
        panel.add(tools);
        return panel;
    }

    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final NoteState state = (NoteState) evt.getNewValue();
        setFields(state);
        if (state.getError() != null) {
            JOptionPane.showMessageDialog(this, state.getError(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setFields(NoteState state) {
        noteInputField.setText(state.getNote());
        noteName.setText(state.getTitle());
    }

    public void setNoteController(NoteController controller) {
        this.noteController = controller;
    }

    /**
     * Updates the translation.
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
    public void displayNewNote(String newNote) {
        outputArea.setText(newNote);
    }
    // add methods for other controllers here
}
