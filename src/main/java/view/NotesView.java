package view;

import java.awt.*;
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
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import org.jetbrains.annotations.NotNull;

import interface_adapter.ai.AiController;
import interface_adapter.note.NoteController;
import interface_adapter.note.NoteState;
import interface_adapter.note.NoteViewModel;

public class NotesView extends JPanel implements ActionListener, PropertyChangeListener {

    private AiController aiController;
    private NoteController noteController;

    private final JLabel noteName = new JLabel("New Note");
    private final JTextArea noteInputField = new JTextArea();

    private final JButton saveButton = new JButton("Save Note");
    private final JButton uploadButton = new JButton("Upload");
    private final JButton clearButton = new JButton("Clear");
    private final JButton deleteButton = new JButton("Delete");

    public NotesView(NoteViewModel noteViewModel) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        noteViewModel.addPropertyChangeListener(this);

        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        mainPanel.add(noteName);
        noteName.setAlignmentX(Component.CENTER_ALIGNMENT);

        noteInputField.setLineWrap(true);
        noteInputField.setWrapStyleWord(true);

        final JPanel buttons = new JPanel();
        buttons.add(saveButton);
        buttons.add(uploadButton);
        buttons.add(clearButton);
        buttons.add(deleteButton);

        saveButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(saveButton)) {
                        final String newNoteName = JOptionPane.showInputDialog("Enter new note name");
                        noteName.setText(newNoteName);
                        noteController.execute(noteInputField.getText(), noteName.getText());

                    }
                }
        );

        uploadButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(uploadButton)) {
                        final JFileChooser fileChooser = new JFileChooser();
                        fileChooser.setDialogTitle("Upload");
                        final int returnVal = fileChooser.showOpenDialog(this);
                        final File selectedFile = fileChooser.getSelectedFile();
                        if (returnVal == JFileChooser.APPROVE_OPTION) {
                            try {
                                final BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
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
        );

        clearButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(clearButton)) {
                        noteController.execute(noteInputField.getText(), noteName.getText());
                    }
                }
        );

        deleteButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(deleteButton)) {
                        noteController.execute(noteInputField.getText(), noteName.getText());
                        noteName.setText("New Note");
                    }
                }
        );

        final JMenuBar menuBar = new JMenuBar();
        mainPanel.add(menuBar);
        mainPanel.add(noteInputField);
        mainPanel.add(buttons);
        this.add(mainPanel);

        final JPanel functionalityPanel = getjPanel();
        this.add(functionalityPanel);

        final JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, mainPanel, functionalityPanel);
        this.add(splitPane);

    }

    @NotNull
    private JPanel getjPanel() {
        final JPanel functionalityPanel = new JPanel();
        functionalityPanel.setLayout(new BoxLayout(functionalityPanel, BoxLayout.Y_AXIS));

        final JPanel tools = new JPanel();

        final JButton aiButton = new JButton("Complete Notes With AI");
        aiButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(aiButton)) {
                        aiController.generateResponse(noteInputField.getText());
                      // Jenna, you may need to call some method since you cannot create an instance of Note here.
                        aiController.generateResponse(new Note(notesTextArea.getText(), "NA"));
                    }
                }
        );
        tools.add(aiButton);

        final JButton languageButton = new JButton("Translate to Other Language");
        languageButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(languageButton)) {
                        // call controller here
                    }
                }
        );
        tools.add(languageButton);

        final JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);

        functionalityPanel.add(outputArea);
        functionalityPanel.add(tools);
        return functionalityPanel;
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
    }

    public void setNoteController(NoteController controller) {
        this.noteController = controller;
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
