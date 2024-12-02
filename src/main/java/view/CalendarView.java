package view;

import javax.swing.*;

import entity.Events;
import interface_adapter.calendar.CalendarController;

import java.awt.*;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                evt -> {
                    if (evt.getSource().equals(loginButton)) {
                        loginButton.setVisible(false);
                        final String email = JOptionPane.showInputDialog(this,
                                "Enter Email:", "Google Account", JOptionPane.PLAIN_MESSAGE);
                        if (email != null && !email.trim().isEmpty()) {
                            try {
                                calendarController.execute(email);
                            }
                            catch (GeneralSecurityException | IOException exception) {
                                throw new RuntimeException(exception);
                            }
                        }
                    }
                }
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

    public void setCalendarController(CalendarController calendarController) {
        this.calendarController = calendarController;
    }
}
