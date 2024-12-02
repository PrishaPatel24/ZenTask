package app;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
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
import interface_adapter.savenote.NoteViewModel;
import interface_adapter.savenote.SaveNoteController;
import interface_adapter.savenote.SaveNotePresenter;
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
    static final String CALENDAR_TITLE = "Calendar";
    static final String NOTES_TITLE = "Notes";
    static final String CHECKLIST_TITLE = "Checklist";

    /**
     * This runs the program, sets up necessary things for the use cases to run the program
     * and allows the user to see the UI.
     * @param args arguments to run MainNoteApplication.main().
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainNoteApplication::run);
    }

    @NotNull
    private static JPanel getButtonPanel(CardLayout cardLayout, JPanel cardPanel) {
        final JButton showCalendarButton = new JButton(CALENDAR_TITLE);
        showCalendarButton.addActionListener(event -> cardLayout.show(cardPanel, CALENDAR_TITLE));

        final JButton showNotesButton = new JButton(NOTES_TITLE);
        showNotesButton.addActionListener(event -> cardLayout.show(cardPanel, NOTES_TITLE));

        final JButton showChecklistButton = new JButton(CHECKLIST_TITLE);
        showChecklistButton.addActionListener(event -> cardLayout.show(cardPanel, CHECKLIST_TITLE));

        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        buttonPanel.add(showCalendarButton);
        buttonPanel.add(showNotesButton);
        buttonPanel.add(showChecklistButton);
        return buttonPanel;
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

    private static void run() {
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
        cardPanel.add(notesPanel, NOTES_TITLE);
        cardPanel.add(calendarPanel, CALENDAR_TITLE);
        cardPanel.add(checklistPanel, CHECKLIST_TITLE);

        final JPanel buttonPanel = getButtonPanel(cardLayout, cardPanel);

        final JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(buttonPanel, BorderLayout.WEST);
        mainPanel.add(cardPanel, BorderLayout.CENTER);

        final JFrame frame = new JFrame("ZenTask");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }
}
