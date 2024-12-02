package view;

import java.awt.FlowLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import interface_adapter.add_task.AddTaskController;
import interface_adapter.add_task.TaskState;
import interface_adapter.add_task.TaskViewModel;

/**
 * The View for when the user is viewing a checklist in the program.
 */
public class ChecklistView extends JPanel implements PropertyChangeListener {

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
                    addTaskController.execute(taskDescription.trim());
                }
            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final TaskState state = (TaskState) evt.getNewValue();

        if (state.getError() != null) {
            JOptionPane.showMessageDialog(this, state.getError(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        final JPanel taskPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        if (!state.getList().isEmpty()) {
            final JCheckBox taskCheckBox = new JCheckBox((state.getList().get(state.getList().size() - 1))
                    .getDescription());
            taskPanel.add(taskCheckBox);
        }
        checklistPanel.add(taskPanel);

        checklistPanel.revalidate();
        checklistPanel.repaint();
    }

    public void setTaskController(AddTaskController controller) {
        this.addTaskController = controller;
    }
}
