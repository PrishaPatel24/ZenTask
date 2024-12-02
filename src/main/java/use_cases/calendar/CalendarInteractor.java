package use_cases.calendar;

import entity.Events;
import com.google.api.services.calendar.model.Event;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class CalendarInteractor implements CalendarInputBoundary {
    private final CalendarOutputBoundary calendarPresenter;
    private final CalendarRequest calendarRequest;
    private final List<Events> events;

    public CalendarInteractor(CalendarOutputBoundary calendarOutputBoundary, CalendarRequest calendarRequest) {
        this.calendarPresenter = calendarOutputBoundary;
        this.calendarRequest = calendarRequest;
        this.events = new ArrayList<Events>();
    }

    public void execute(String email) throws GeneralSecurityException, IOException {
        final List<Event> retResult = calendarRequest.getCalendarEvents(email);
        for (Event value : retResult) {
            final Events event = new Events(value.getSummary(),
                    value.getStart(), value.getLocation(), value.getDescription()
            );
            events.add(event);
        }
        System.out.println("finished processing API");
        calendarPresenter.prepareSuccessView(events);
    }
}
