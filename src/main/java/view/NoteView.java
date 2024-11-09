package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.*;

import interface_adapter.note.NoteController;
import interface_adapter.note.NoteState;
import interface_adapter.note.NoteViewModel;

/**
 * The View for when the user is viewing a note in the program.
 */
public class NoteView extends JPanel implements ActionListener, PropertyChangeListener {

    private final NoteViewModel noteViewModel;

    private final JLabel noteName = new JLabel("Actions");
    private final JTextArea noteInput = new JTextArea();

    private final JButton saveButton = new JButton("Save");
    private final JButton viewButtton = new JButton("View");
    private final JButton deleteButton = new JButton("Delete");
    private final JButton uploadButton = new JButton("Upload");

    private NoteController noteController;

    public NoteView(NoteViewModel noteViewModel) {

        noteName.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.noteViewModel = noteViewModel;
        this.noteViewModel.addPropertyChangeListener(this);
        noteInput.setText(" ");
        add(noteInput);

        final JPanel buttons = new JPanel();
        buttons.add(saveButton);
        buttons.add(viewButtton);
        buttons.add(deleteButton);
        buttons.add(uploadButton);

        saveButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(saveButton)) {
                        final JFileChooser fileChooser = new JFileChooser();
                        fileChooser.setDialogTitle("Save Note");
                        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                        final int returnVal = fileChooser.showOpenDialog(this);
                        if (returnVal == JFileChooser.APPROVE_OPTION) {
                            final File file = fileChooser.getSelectedFile();
                            try {
                                if (file.exists()) {
                                    final BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                                    writer.write(noteInput.getText());
                                }
                            }
                            catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        noteController.execute(noteInput.getText());

                    }
                }
        );

        deleteButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(deleteButton)) {
                        noteInput.setText("");
                    }
                }
        );

        viewButtton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(viewButtton)) {
                        final JFileChooser fileChooser = new JFileChooser();
                        fileChooser.setDialogTitle("View");
                        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                        final int returnVal = fileChooser.showOpenDialog(this);
                        if (returnVal == JFileChooser.APPROVE_OPTION) {
                            try {
                                String text = " ";
                                for (String line : Files.readAllLines(fileChooser.getSelectedFile().toPath())) {
                                    text += line + "\n";
                                    noteInput.setText(text);
                                }
                            }
                            catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(noteName);
        this.add(noteInput);
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
        noteInput.setText(state.getNote());
    }

    public void setNoteController(NoteController controller) {
        this.noteController = controller;
    }
}

