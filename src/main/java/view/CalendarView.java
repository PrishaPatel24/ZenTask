package view;

import javax.swing.*;

import interface_adapter.calendar.CalendarController;

/**
 * The view for the calendar use case.
 */
public class CalendarView extends JPanel {
    private final JLabel promptLabel;
    private final JButton loginButton;
    private CalendarController calendarController;

    public CalendarView() {
        this.promptLabel = new JLabel("Login to Google Calendar here.");
        this.loginButton = new JButton("Login");
        loginButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(loginButton)) {
                        // some action to do here
                    }
                }
        );
        this.add(this.promptLabel);
        this.add(this.loginButton);
    }

    public void setCalendarController(CalendarController calendarController) {
        this.calendarController = calendarController;
    }
}
