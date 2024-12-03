package use_cases.calendar;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import com.google.api.services.calendar.model.Event;
import entity.Events;

/**
 * This class executes the Calendar Use Case.
 * It has three private fields: calendarPresenter communicates what events will be displayed,
 * calendarRequest that retrieves events from the user's Google Calendar and events that stores the events from the
 * Google Calendar.
 * */
public class CalendarInteractor implements CalendarInputBoundary {
    private final CalendarOutputBoundary calendarPresenter;
    private final CalendarRequest calendarRequest;
    private final List<Events> events;

    public CalendarInteractor(CalendarOutputBoundary calendarOutputBoundary, CalendarRequest calendarRequest) {
        this.calendarPresenter = calendarOutputBoundary;
        this.calendarRequest = calendarRequest;
        this.events = new ArrayList<>();
    }

    /**
     * This checks the validity of the email and requests for the user's Google Calendar if the email is valid.
     * @param email String of the user's email address that is used to retrieve the Google calendar.
     * @throws GeneralSecurityException is thrown if there was an issue with logging into the user's Google account.
      * @throws IOException is thrown if there was an issue getting the events information from the Google Calendar
     *         and displaying it to the user.
     */
    public void execute(String email) throws GeneralSecurityException, IOException {
        if (email == null || email.isEmpty()) {
            calendarPresenter.prepareFailureView("Email is required!");
        }
        else if (!email.contains("@gmail.com")) {
            calendarPresenter.prepareFailureView("Invalid email format!");
        }
        else {
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
}
