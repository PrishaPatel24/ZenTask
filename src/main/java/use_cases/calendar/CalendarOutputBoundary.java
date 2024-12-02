package use_cases.calendar;

import java.util.List;

import entity.Events;

/**
 * The output boundary for the calendar use case.
 */
public interface CalendarOutputBoundary {
    /**
     * Prepares the success view for the calendar Use Case.
     * @param events the output data as a list of events
     */
    void prepareSuccessView(List<Events> events);

    /**
     * Prepares the failure view for the Calendar Use Case.
     * @param message String of the error displayed to the user.
     */
    void prepareFailureView(String message);
}
