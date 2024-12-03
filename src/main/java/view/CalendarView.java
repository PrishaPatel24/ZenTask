package view;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import entity.Events;
import interface_adapter.calendar.CalendarController;

/**
 * The view for the calendar use case.
 */
public class CalendarView extends JPanel {
    private final JLabel promptLabel;
    private final JButton loginButton;
    private final JPanel weekPanel;
    private CalendarController calendarController;

    public CalendarView() throws GeneralSecurityException, IOException {
        this.promptLabel = new JLabel("Please access your Google account:");
        this.loginButton = new JButton("Enter Email");
        this.weekPanel = new JPanel();

        loginButton.addActionListener(
                this::loginAction
        );
        this.add(this.promptLabel);
        this.add(this.loginButton);
    }

    /**
     * Displays events in the view based on the data from the API.
     * @param events list of events from the API
     */
    public void displayEvents(List<Events> events) {
        weekPanel.setLayout(new BoxLayout(weekPanel, BoxLayout.Y_AXIS));
        for (Events event : events) {
            final JPanel eventPanel = new JPanel();
            eventPanel.add(new JLabel(event.getEventName()));
            eventPanel.add(new JLabel(event.getEventLocation()));
            eventPanel.add(new JLabel(event.getEventDatetime()));
            eventPanel.add(new JLabel(event.getEventNotes()));
            weekPanel.add(eventPanel);
        }
        this.promptLabel.setText("Please access your Google account:");
        this.add(this.weekPanel);
        promptLabel.setVisible(false);
    }

    /**
     * This displays the error message that cause the events not to be displayed.
     * This is caused by invalid email: if user enter an email of the wrong domain (not @gmail.com) or enters no email.
     * @param message String of the error that is displayed on the UI.
     */
    public void displayError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void setCalendarController(CalendarController calendarController) {
        this.calendarController = calendarController;
    }

    private void loginAction(ActionEvent evt) {
        if (evt.getSource().equals(loginButton)) {
            loginButton.setVisible(false);
            final String email = JOptionPane.showInputDialog(this,
                    "Enter Email:", "Google Account", JOptionPane.PLAIN_MESSAGE);
            try {
                calendarController.execute(email);
            }
            catch (GeneralSecurityException | IOException exception) {
                throw new RuntimeException(exception);
            }
        }
    }
}
