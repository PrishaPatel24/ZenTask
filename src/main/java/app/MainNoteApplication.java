package app;

import interface_adapter.add_task.AddTaskController;
import interface_adapter.add_task.AddTaskPresenter;
import interface_adapter.add_task.TaskViewModel;
import interface_adapter.calendar.CalendarController;
import interface_adapter.calendar.CalendarPresenter;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

import use_cases.add_task.AddTaskInputBoundary;
import use_cases.add_task.AddTaskInteractor;
import use_cases.add_task.AddTaskOutputBoundary;
import use_cases.calendar.CalendarInputBoundary;
import use_cases.calendar.CalendarInteractor;
import use_cases.calendar.CalendarOutputBoundary;
import use_cases.calendar.CalendarRequest;
import view.CalendarView;
import view.ChecklistView;
import view.DashboardView;
import view.NotesView;

/**
 * The main application to boot the program.
 */
public class MainNoteApplication {
    static final int WIDTH = 850;
    static final int HEIGHT = 500;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            final CardLayout cardLayout = new CardLayout();
            final JPanel cardPanel = new JPanel(cardLayout);

            final JPanel dashboardPanel = new DashboardView();
            final JPanel notesPanel = new NotesView();
            final JPanel calendarPanel = createCalendar();
            final JPanel checklistPanel = createChecklist();

            cardPanel.add(dashboardPanel, "Dashboard");
            cardPanel.add(notesPanel, "Notes");
            cardPanel.add(calendarPanel, "Calendar");
            cardPanel.add(checklistPanel, "Checklist");

            final JPanel buttonPanel = getButtonPanel(cardLayout, cardPanel);

            final JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.add(buttonPanel, BorderLayout.WEST);
            mainPanel.add(cardPanel, BorderLayout.CENTER);

            final JFrame frame = new JFrame("ZenTask");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setSize(WIDTH, HEIGHT);
            frame.setContentPane(mainPanel);
            frame.setVisible(true);
        });
    }

    @NotNull
    private static JPanel getButtonPanel(CardLayout cardLayout, JPanel cardPanel) {
        final JButton showCalendarButton = new JButton("Calendar");
        showCalendarButton.addActionListener(event -> cardLayout.show(cardPanel, "Calendar"));

        final JButton showNotesButton = new JButton("Notes");
        showNotesButton.addActionListener(event -> cardLayout.show(cardPanel, "Notes"));

        final JButton showChecklistButton = new JButton("Checklist");
        showChecklistButton.addActionListener(event -> cardLayout.show(cardPanel, "Checklist"));

        final JButton homeButton = new JButton("Dashboard");
        homeButton.addActionListener(event -> cardLayout.show(cardPanel, "Dashboard"));

        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(homeButton);
        buttonPanel.add(showCalendarButton);
        buttonPanel.add(showNotesButton);
        buttonPanel.add(showChecklistButton);
        return buttonPanel;
    }

    private static JPanel createDashboard() {
        final JPanel dashboardPanel = new JPanel();
        dashboardPanel.add(new JLabel("Welcome to your Dashboard"));
        return dashboardPanel;
    }

    private static JPanel createCalendar() {
        final CalendarView calendarView = new CalendarView();
        final CalendarOutputBoundary calendarPresenter = new CalendarPresenter(calendarView);
        final CalendarInputBoundary calendarInteractor = new CalendarInteractor(calendarPresenter, new CalendarRequest());
        final CalendarController calendarController = new CalendarController(calendarInteractor);
        calendarView.setCalendarController(calendarController);
        return calendarView;
    }

    private static JPanel createNotes() {
        final JPanel notesPanel = new JPanel();
        notesPanel.setLayout(new BoxLayout(notesPanel, BoxLayout.X_AXIS));

        final TextArea textArea = new TextArea();
        notesPanel.add(textArea);

        final JPanel toolBarPanel = new JPanel();
        toolBarPanel.setLayout(new BoxLayout(toolBarPanel, BoxLayout.Y_AXIS));
        final JButton aiButton = new JButton("Complete Notes With AI");
        toolBarPanel.add(aiButton);

        final JButton languageButton = new JButton("Translate to Other Language");
        toolBarPanel.add(languageButton);

        final JTextArea notesTextArea = new JTextArea();
        notesTextArea.setEditable(false);
        notesTextArea.setSize(75, 50);
        toolBarPanel.add(notesTextArea);

        notesPanel.add(toolBarPanel);
        return notesPanel;
    }

    private static JPanel createChecklist() {
        final TaskViewModel taskViewModel = new TaskViewModel();
        final AddTaskOutputBoundary addTaskPresenter = new AddTaskPresenter(taskViewModel);
        final AddTaskInputBoundary addTaskUseCaseInteractor = new AddTaskInteractor(addTaskPresenter);
        final AddTaskController controller = new AddTaskController(addTaskUseCaseInteractor);
        final ChecklistView checklistView = new ChecklistView(taskViewModel);
        checklistView.setTaskController(controller);
        taskViewModel.firePropertyChanged();
        return checklistView;
    }
}
