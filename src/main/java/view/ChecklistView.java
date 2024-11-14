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

    public ChecklistView(TaskViewModel taskViewModel) {
        this.taskViewModel = taskViewModel;
        this.taskViewModel.addPropertyChangeListener(this);

        this.checklistPanel = new JPanel();
        this.addTaskButton = new JButton("Add Task +");

        checklistPanel.setLayout(new BoxLayout(checklistPanel, BoxLayout.Y_AXIS));
        checklistPanel.add(new JLabel("Tasks:"));
        checklistPanel.add(addTaskButton);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(checklistPanel);

        addTaskButton.addActionListener(evt -> {
            if (evt.getSource().equals(addTaskButton)) {
                final String taskDescription = JOptionPane.showInputDialog(this,
                        "Enter Task Description:", "New Task", JOptionPane.PLAIN_MESSAGE);
                if (taskDescription != null && !taskDescription.trim().isEmpty()) {
                    addTask(taskDescription.trim());
                }
            }
        });
    }

    /**
     * Adds a new task to the checklist as a JCheckBox.
     * @param taskDescription the description of the new task
     */
    private void addTask(String taskDescription) {
        final JPanel taskPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        final JCheckBox taskCheckBox = new JCheckBox(taskDescription);
        taskPanel.add(taskCheckBox);
        checklistPanel.add(taskPanel);

        addTaskController.execute(taskDescription);
        checklistPanel.revalidate();
        checklistPanel.repaint();
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
        // setFields(state);
        if (state.getError() != null) {
            JOptionPane.showMessageDialog(this, state.getError(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

//    private void setFields(TaskState state) {
//        state.setTask("test description");
//    }

    public void setTaskController(AddTaskController controller) {
        this.addTaskController = controller;
    }
}
