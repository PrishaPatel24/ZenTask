package view;

import interface_adapter.ai.AiController;
import interface_adapter.note.NoteController;
import interface_adapter.note.NoteState;
import interface_adapter.note.NoteViewModel;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class NotesView extends JPanel implements ActionListener, PropertyChangeListener {

    private AiController aiController;
    private NoteController noteController;
    private final NoteViewModel noteViewModel;

    private final JLabel noteName = new JLabel("New Note");
    private final JTextArea noteInputField = new JTextArea();
    private final JMenuBar menuBar = new JMenuBar();

    private final JButton addButton = new JButton("Add Title");
    private final JButton uploadButton = new JButton("Upload");
    private final JButton clearButton = new JButton("Clear");
    private final JButton deleteButton = new JButton("Delete");

    private final JMenuItem boldMenu = new JMenuItem("Bold");
    private final JMenuItem italicMenu = new JMenuItem("Italic");
    private final JMenuItem underlineMenu = new JMenuItem("Underline");
    private final JMenu redoMenu = new JMenu("Redo");
    private final JMenu undoMenu = new JMenu("Undo");

    public NotesView(NoteViewModel noteViewModel) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.noteViewModel = noteViewModel;
        this.noteViewModel.addPropertyChangeListener(this);

        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        mainPanel.add(noteName);
        noteName.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        buttons.add(addButton);
        buttons.add(uploadButton);
        buttons.add(clearButton);
        buttons.add(deleteButton);

        addButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(addButton)) {
                        final String newNoteName = JOptionPane.showInputDialog("Enter new note name");
                        noteName.setText(newNoteName);
                        noteController.save(noteInputField.getText(), noteName.getText());

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
                                noteController.updateContent(noteInputField.getText());
                            }
                            catch (IOException exception) {
                                JOptionPane.showMessageDialog(this, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }

                    }
                }
        );

        clearButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(clearButton)) {
                        noteController.clear(noteInputField.getText());
                    }
                }
        );

        deleteButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(deleteButton)) {
                        noteController.clear(noteInputField.getText());
                        noteName.setText("New Note");
                    }
                }
        );

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

    // add methods for other controllers here
}
