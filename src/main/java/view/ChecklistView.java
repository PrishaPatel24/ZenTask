package view;

import interface_adapter.add_task.AddTaskController;
import interface_adapter.add_task.TaskState;
import interface_adapter.add_task.TaskViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View for when the user is viewing a checklist in the program.
 */
public class ChecklistView extends JPanel implements ActionListener, PropertyChangeListener {

    private final TaskViewModel taskViewModel;

    private final JLabel title = new JLabel("Title:");
    private final JTextArea titleInputField = new JTextArea();

    private final JTextArea taskInputField = new JTextArea();
    private final JButton checkbox = new JButton();

    private AddTaskController addTaskController;

    public ChecklistView(TaskViewModel taskViewModel) {

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.taskViewModel = taskViewModel;
        this.taskViewModel.addPropertyChangeListener(this);

        final JPanel checkboxes = new JPanel();
        checkboxes.add(checkbox);

        checkbox.addActionListener(
                evt -> {
                    if (evt.getSource().equals(checkbox)) {
                        addTaskController.execute(null);

                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(titleInputField);
        this.add(checkboxes);
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
        final TaskState state = (TaskState) evt.getNewValue();
        setFields(state);
        if (state.getError() != null) {
            JOptionPane.showMessageDialog(this, state.getError(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setFields(TaskState state) {
        taskInputField.setText(state.getTask().toString());
    }

    public void setTaskController(AddTaskController controller) {
        this.addTaskController = controller;
    }

}
