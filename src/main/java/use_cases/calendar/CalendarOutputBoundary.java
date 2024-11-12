package use_cases.calendar;

import java.util.List;

import entity.Event;

/**
 * The output boundary for the calendar use case.
 */
public interface CalendarOutputBoundary {
    /**
     * Prepares the success view for the calendar Use Case.
     * @param events the output data as a list of events
     */
    void prepareSuccessView(List<Event> events);
}