package view;

import javax.swing.*;
import java.lang.reflect.Array;

/**
 * The view and taking in the inputs for the checklist section of the program.
 */
public class ChecklistView extends JPanel {
    private final JButton taskButton;
    private final JLabel label;
    // @prisha fix everything here pls

    public ChecklistView() {
        final JPanel checklistPanel = new JPanel();
        this.label = new JLabel("Tasks go here!");
        checklistPanel.add(label);
        this.taskButton = new JButton("Add Task +");
        checklistPanel.add(taskButton);

        taskButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(taskButton)) {
                        // add ChecklistController.dosomething() based
                        // on button
                    }
                }
        );
    }

    // add checklist controller here
}
