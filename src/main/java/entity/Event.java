package entity;

/**
 * The representation of a calendar event for our program.
 */
public class Event {

    private String name;
    private String datetime;
    private String location;
    private String notes;

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

    public void setEventName(String eventName) {
        this.name = eventName;
    }

    public void setEventDatetime(String eventDatetime) {
        this.datetime = eventDatetime;
    }

    public void setEventLocation(String eventLocation) {
        this.location = eventLocation;
    }

    public void setEventNotes(String eventNotes) {
        this.notes = eventNotes;
    }
}
