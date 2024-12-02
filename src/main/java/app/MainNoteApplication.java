package app;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.jetbrains.annotations.NotNull;

import data_access.InMemorySaveNoteDataAccessObject;
import interface_adapter.add_task.AddTaskController;
import interface_adapter.add_task.AddTaskPresenter;
import interface_adapter.add_task.TaskViewModel;
import interface_adapter.ai.AiController;
import interface_adapter.ai.AiPresenter;
import interface_adapter.calendar.CalendarController;
import interface_adapter.calendar.CalendarPresenter;
import interface_adapter.savenote.SaveNoteController;
import interface_adapter.savenote.SaveNotePresenter;
import interface_adapter.savenote.NoteViewModel;
import interface_adapter.translation.TranslationController;
import interface_adapter.translation.TranslationPresenter;
import use_cases.add_task.AddTaskInputBoundary;
import use_cases.add_task.AddTaskInteractor;
import use_cases.add_task.AddTaskOutputBoundary;
import use_cases.ai.AiInputBoundary;
import use_cases.ai.AiInteractor;
import use_cases.ai.AiOutputBoundary;
import use_cases.ai.AiRequest;
import use_cases.calendar.CalendarInputBoundary;
import use_cases.calendar.CalendarInteractor;
import use_cases.calendar.CalendarOutputBoundary;
import use_cases.calendar.CalendarRequest;
import use_cases.savenote.SaveNoteInputBoundary;
import use_cases.savenote.SaveNoteInteractor;
import use_cases.savenote.SaveNoteOutputBoundary;
import use_cases.translation.TranslationInputBoundary;
import use_cases.translation.TranslationInteractor;
import use_cases.translation.TranslationOutputBoundary;
import view.CalendarView;
import view.ChecklistView;
import view.DashboardView;
import view.NotesView;

/**
 * The main application to boot the program.
 */
public class MainNoteApplication {
    static final int WIDTH = 1100;
    static final int HEIGHT = 500;

    /**
     * This runs the program, sets up necessary things for the use cases to run the program
     * and allows the user to see the UI.
     * @param args arguments to run MainNoteApplication.main().
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            final CardLayout cardLayout = new CardLayout();
            final JPanel cardPanel = new JPanel(cardLayout);

            final JPanel dashboardPanel = new DashboardView();
            final JPanel notesPanel = createNotes();
            final JPanel calendarPanel;
            try {
                calendarPanel = createCalendar();
            }
            catch (GeneralSecurityException | IOException exception) {
                throw new RuntimeException(exception);
            }
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

        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        buttonPanel.add(showCalendarButton);
        buttonPanel.add(showNotesButton);
        buttonPanel.add(showChecklistButton);
        return buttonPanel;
    }

    private static JPanel createDashboard() {
        // TODO: delete if not needed.
        final JPanel dashboardPanel = new JPanel();
        dashboardPanel.add(new JLabel("Welcome to your Dashboard"));
        return dashboardPanel;
    }

    private static JPanel createCalendar() throws GeneralSecurityException, IOException {
        final CalendarView calendarView = new CalendarView();
        final CalendarOutputBoundary calendarPresenter = new CalendarPresenter(calendarView);
        final CalendarInputBoundary calendarInteractor = new CalendarInteractor(calendarPresenter,
                new CalendarRequest());
        final CalendarController calendarController = new CalendarController(calendarInteractor);
        calendarView.setCalendarController(calendarController);
        return calendarView;
    }

    private static JPanel createNotes() {
        final NoteViewModel noteViewModel = new NoteViewModel();
        final SaveNoteOutputBoundary notePresenter = new SaveNotePresenter(noteViewModel);
        final InMemorySaveNoteDataAccessObject inMemoryNoteDAO = new InMemorySaveNoteDataAccessObject();
        final SaveNoteInputBoundary noteInteractor = new SaveNoteInteractor(notePresenter, inMemoryNoteDAO);
        final SaveNoteController controller = new SaveNoteController(noteInteractor);
        final NotesView notesView = new NotesView(noteViewModel);
        notesView.setNoteController(controller);
      
        final AiOutputBoundary aiPresenter = new AiPresenter(notesView);
        final AiRequest aiRequest = new AiRequest();
        final AiInputBoundary aiInteractor = new AiInteractor(aiPresenter, aiRequest);
        final AiController aiController = new AiController(aiInteractor);
        notesView.setAiController(aiController);

        final TranslationOutputBoundary translationPresenter = new TranslationPresenter(notesView);
        final TranslationInputBoundary translationInteractor = new TranslationInteractor(translationPresenter);
        final TranslationController translationController = new TranslationController(translationInteractor);
        notesView.setTranslationController(translationController);

        return notesView;
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
