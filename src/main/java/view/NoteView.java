package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;

import javax.swing.*;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;

import interface_adapter.note.NoteController;
import interface_adapter.note.NoteState;
import interface_adapter.note.NoteViewModel;

/**
 * The View for when the user is viewing a note in the program.
 */
public class NoteView extends JPanel implements ActionListener, PropertyChangeListener {

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

    private NoteController noteController;

    public NoteView(NoteViewModel noteViewModel) {

        noteName.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.noteViewModel = noteViewModel;
        this.noteViewModel.addPropertyChangeListener(this);

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

        final JMenu editMenu = new JMenu("Edit");
        editMenu.add(boldMenu);
        editMenu.add(italicMenu);
        editMenu.add(underlineMenu);
        menuBar.add(editMenu);
        menuBar.add(redoMenu);
        menuBar.add(undoMenu);

        boldMenu.addActionListener(
                evt -> {
                    final JTextPane textPane = new JTextPane();
                    noteInputField.add(textPane);
                    textPane.setEditorKit(new StyledEditorKit());
                    final StyledEditorKit.BoldAction boldAction = new StyledEditorKit.BoldAction();
                    boldAction.actionPerformed(evt);
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(noteName);
        this.add(menuBar);
        this.add(noteInputField);
        this.add(buttons);

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
}

