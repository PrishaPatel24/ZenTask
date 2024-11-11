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
    private AddTaskController addTaskController;

    private final JPanel checklistPanel;
    private final JButton addTaskButton;
    private final JTextArea taskInputField;


    public ChecklistView(TaskViewModel taskViewModel) {
        this.checklistPanel = new JPanel();
        this.addTaskButton = new JButton("Add Task +");
        this.taskInputField = new JTextArea();
        taskInputField.setSize(10, 10);
        taskInputField.setVisible(false);

        this.checklistPanel.add(new JLabel("Tasks go here!"));
        checklistPanel.add(addTaskButton);

        final JButton emptyBox = new JButton(" ");
        final JButton checkedBox = new JButton("X");
        emptyBox.setPreferredSize(checkedBox.getPreferredSize());
        emptyBox.setVisible(false);
        checkedBox.setVisible(false);

        addTaskButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(addTaskButton)) {
                        // add ChecklistController.dosomething() based
                        // on button
                        taskInputField.setVisible(true);
                        emptyBox.setVisible(true);
                        addTaskController.execute(taskInputField.getText());
                    }
                }
        );

        emptyBox.addActionListener(
                evt -> {
                    if (evt.getSource().equals(emptyBox)) {
                        emptyBox.setVisible(false);
                        checkedBox.setVisible(true);
                    }
                }
        );

        checkedBox.addActionListener(
                evt -> {
                    if (evt.getSource().equals(checkedBox)) {
                        checkedBox.setVisible(false);
                        emptyBox.setVisible(true);
                    }
                }
        );

        this.taskViewModel = taskViewModel;
        this.taskViewModel.addPropertyChangeListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        final JPanel taskPanel = new JPanel();
        taskPanel.add(emptyBox);
        taskPanel.add(checkedBox);
        taskPanel.add(taskInputField);
        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.X_AXIS));

        checklistPanel.setLayout(new BoxLayout(checklistPanel, BoxLayout.Y_AXIS));
        checklistPanel.add(taskPanel);
        this.add(checklistPanel);
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
