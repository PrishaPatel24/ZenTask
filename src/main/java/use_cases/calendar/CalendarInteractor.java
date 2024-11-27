package use_cases.calendar;

import entity.Event;

import java.util.ArrayList;
import java.util.List;

public class CalendarInteractor implements CalendarInputBoundary {
    private final CalendarOutputBoundary calendarPresenter;
    private final CalendarRequest calendarRequest;
    private final List<Event> events;

    public CalendarInteractor(CalendarOutputBoundary calendarOutputBoundary, CalendarRequest calendarRequest) {
        this.calendarPresenter = calendarOutputBoundary;
        this.calendarRequest = calendarRequest;
        this.events = new ArrayList<>();
    }

    public void execute(Event event) {
        // TODO jenna set these events based on what the api request returns
//        for (event : events) {
//            event.setEventName();
//            event.setEventDatetime();
//            event.setEventLocation();
//            event.setEventNotes();
//        }
        calendarPresenter.prepareSuccessView(events);
    }
}
