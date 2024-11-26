package entity;

import org.junit.Test;

public class EventTest {
    @Test
    public void getEventNameTest() {
        final Event testEvent = new Event("test name", "2024-11-18",
                "test location", "extra notes go here");
        assert testEvent.getEventName().equals("test name");
    }

    @Test
    public void getEventLocationTest() {
        final Event testEvent = new Event("test name", "2024-11-18",
                "test location", "extra notes go here");
        assert testEvent.getEventLocation().equals("test location");
    }

    @Test
    public void getDateTimeTest() {
        final Event testEvent = new Event("test name", "2024-11-18",
                "test location", "extra notes go here");
        assert testEvent.getEventDatetime().equals("2024-11-18");
    }

    @Test
    public void getNoteTest() {
        final Event testEvent = new Event("test name", "2024-11-18",
                "test location", "extra notes go here");
        assert testEvent.getEventNotes().equals("extra notes go here");
    }
}
