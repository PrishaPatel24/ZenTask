package use_cases.calendar;

import entity.Event;

/**
 * The input boundary taking in an event.
 */
public interface CalendarInputBoundary {
    /**
     * Executes the calendar use case.
     * @param event to be executed
     */
    void execute(Event event);
}
