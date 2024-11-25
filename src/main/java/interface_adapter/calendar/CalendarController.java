package interface_adapter.calendar;

import entity.Event;
import use_cases.calendar.CalendarInputBoundary;

/**
 * The controller for the calendar use case.
 */
public class CalendarController {
    private final CalendarInputBoundary calendarUseCaseInteractor;

    public CalendarController(CalendarInputBoundary calendarUseCaseInteractor) {
        this.calendarUseCaseInteractor = calendarUseCaseInteractor;
    }

    /**
     * Executes the calendar Use Case.
     * @param event the calendar event
     */
    public void execute(Event event) {
        calendarUseCaseInteractor.execute(event);
    }
}
