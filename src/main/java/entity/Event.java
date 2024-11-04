package entity;

/**
 * The representation of a calendar event for our program.
 */
public class Event {

    private final String name;
    private final String datetime;
    private final String location;
    private final String notes;

    public Event(String name, String datetime, String location, String notes) {
        this.name = name;
        this.datetime = datetime;
        this.location = location;
        this.notes = notes;
    }

    public String getEventName() {
        return name;
    }

    public String getEventDatetime() {
        return datetime;
    }

    public String getEventLocation() {
        return location;
    }

    public String getEventNotes() {
        return notes;
    }

}
