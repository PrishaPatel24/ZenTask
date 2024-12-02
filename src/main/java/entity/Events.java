package entity;

import com.google.api.services.calendar.model.EventDateTime;

/**
 * The representation of a calendar event for our program.
 */
public class Events {

    private String name;
    private EventDateTime datetime;
    private String location;
    private String notes;

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

    public void setEventName(String eventName) {
        this.name = eventName;
    }

    public void setEventDatetime(EventDateTime eventDatetime) {
        this.datetime = eventDatetime;
    }

    public void setEventLocation(String eventLocation) {
        this.location = eventLocation;
    }

    public void setEventNotes(String eventNotes) {
        this.notes = eventNotes;
    }
}
