package view;

import interface_adapter.ai.AiController;
import use_cases.ai.AiInteractor;

import javax.swing.*;
import java.awt.*;

public class NotesView extends JPanel {
    private final JTextArea notesTextArea;
    private final JPanel toolBarPanel;
    private final JButton aiButton;
    private final JButton languageButton;
    private final JTextArea outputArea;
    private AiController aiController;
    // add controllers and other view methods as necessary for language and note use cases

    public NotesView() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        final JPanel igeePanel = new JPanel();
        this.notesTextArea = new JTextArea();
        igeePanel.add(notesTextArea);
        this.add(notesTextArea);
        // @Igee the Notes text area is basically just a field to where youd add your stuff.

        this.toolBarPanel = new JPanel();
        toolBarPanel.setLayout(new BoxLayout(toolBarPanel, BoxLayout.Y_AXIS));
        this.aiButton = new JButton("Complete Notes With AI");
        aiButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(aiButton)) {
                        aiController.generateResponse(notesTextArea.getText());
                    }
                }
        );
        toolBarPanel.add(aiButton);

        this.languageButton = new JButton("Translate to Other Language");
        languageButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(languageButton)) {
                        // call controlller here
                    }
                }
        );
        toolBarPanel.add(languageButton);

        this.outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setSize(75, 50);
        toolBarPanel.add(outputArea);

        this.add(toolBarPanel);
    }

    public void setAiController(AiController aiController) {
        this.aiController = aiController;
    }

    // add methods for other controllers here
}
