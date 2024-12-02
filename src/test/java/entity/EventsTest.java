package entity;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.EventDateTime;
import org.junit.Test;

public class EventsTest {
    @Test
    public void getEventNameTest() {
        final Events testEvents = new Events("test name", new EventDateTime().setDateTime(new DateTime("2024-12-02T09:00:00.000-05:00")),
                "test location", "extra notes go here");
        assert testEvents.getEventName().equals("test name");
    }

    @Test
    public void getEventLocationTest() {
        final Events testEvents = new Events("test name", new EventDateTime().setDateTime(new DateTime("2024-12-02T09:00:00.000-05:00")),
                "test location", "extra notes go here");
        assert testEvents.getEventLocation().equals("test location");
    }

    @Test
    public void getDateTimeTest() {
        final Events testEvents = new Events("test name", new EventDateTime().setDateTime(new DateTime("2024-12-02T09:00:00.000-05:00")),
                "test location", "extra notes go here");
        assert testEvents.getEventDatetime().equals("2024-12-02T09:00:00.000-05:00");
    }

    @Test
    public void getNoteTest() {
        final Events testEvents = new Events("test name", new EventDateTime().setDateTime(new DateTime("2024-12-02T09:00:00.000-05:00")),
                "test location", "extra notes go here");
        assert testEvents.getEventNotes().equals("extra notes go here");
    }
}
