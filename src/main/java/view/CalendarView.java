package view;

import javax.swing.*;

import entity.Event;
import interface_adapter.calendar.CalendarController;

import java.awt.*;
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

    public CalendarView() {
        this.promptLabel = new JLabel("Login to Google Calendar here.");
        this.loginButton = new JButton("Login");
        this.weekPanel = new JPanel(new GridLayout(1, 7));

        loginButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(loginButton)) {
                        loginButton.setVisible(false);
                        List<Map<String, String>> data = new ArrayList<>();
                        HashMap<String, String> event1 = new HashMap<>();
                        HashMap<String, String> event2 = new HashMap<>();
                        event1.put("name", "event1");
                        event2.put("name", "event2");
                        event1.put("datetime", "datetime1");
                        event2.put("datetime", "datetime2");
                        event1.put("location", "location1");
                        event2.put("location", "location2");
                        event1.put("notes", "notes1");
                        event2.put("notes", "notes2");
                        data.add(event1);
                        data.add(event2);

                        for (Map event : data) {
                            final JPanel eventPanel = new JPanel();
                            eventPanel.setLayout(new BoxLayout(eventPanel, BoxLayout.Y_AXIS));
                            eventPanel.add(new JButton((String) event.get("name")));
                            eventPanel.add(new JButton((String) event.get("datetime")));
                            eventPanel.add(new JButton((String) event.get("location")));
                            eventPanel.add(new JButton((String) event.get("notes")));
                            weekPanel.add(eventPanel);
                            // jenna api stuff for current calendar
                        }
                    }
                }
        );
        this.add(this.promptLabel);
        this.add(this.loginButton);
        this.add(this.weekPanel, BorderLayout.CENTER);
    }

    /**
     * Displays events in the view based on the data from the API.
     * @param events list of events from the API
     */
    public void displayEvents(List<Event> events) {
        List<Map<String, String>> data = new ArrayList<>();
        HashMap<String, String> event1 = new HashMap<>();
        HashMap<String, String> event2 = new HashMap<>();
        event1.put("name", "event1");
        event2.put("name", "event2");
        event1.put("datetime", "datetime1");
        event2.put("datetime", "datetime2");
        event1.put("location", "location1");
        event2.put("location", "location2");
        event1.put("notes", "notes1");
        event2.put("notes", "notes2");
        data.add(event1);
        data.add(event2);

        for (Map event : data) {
            final JPanel eventPanel = new JPanel();
            eventPanel.setLayout(new BoxLayout(eventPanel, BoxLayout.Y_AXIS));
            eventPanel.add(new JLabel((String) event.get("name")));
            eventPanel.add(new JLabel((String) event.get("location")));
            eventPanel.add(new JLabel((String) event.get("datetime")));
            eventPanel.add(new JLabel((String) event.get("notes")));
        }
    }

    public void setCalendarController(CalendarController calendarController) {
        this.calendarController = calendarController;
    }
}
