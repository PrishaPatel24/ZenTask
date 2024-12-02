package entity;

import com.google.api.services.calendar.model.EventDateTime;

/**
 * The representation of a calendar event for our program.
 */
public class Events {

    private final String name;
    private final EventDateTime datetime;
    private final String location;
    private final String notes;

    public Events(String name, EventDateTime datetime, String location, String notes) {
        this.name = name;
        this.datetime = datetime;
        this.location = location;
        this.notes = notes;
    }

    public String getEventName() {
        return name;
    }

    public String getEventDatetime() {
        return datetime.getDateTime().toString();
    }

    public String getEventLocation() {
        return location;
    }

    public String getEventNotes() {
        return notes;
    }
}
