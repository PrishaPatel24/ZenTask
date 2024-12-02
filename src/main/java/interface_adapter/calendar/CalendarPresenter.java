package interface_adapter.calendar;

import java.util.List;

import entity.Events;
import use_cases.calendar.CalendarOutputBoundary;
import view.CalendarView;

/**
 * The presenter for the calendar use case.
 */
public class CalendarPresenter implements CalendarOutputBoundary {
    private final CalendarView calendarView;

    public CalendarPresenter(CalendarView calendarView) {
        this.calendarView = calendarView;
    }

    @Override
    public void prepareSuccessView(List<Events> events) {
        calendarView.displayEvents(events);
    }

    @Override
    public void prepareFailureView(String message) {
        calendarView.displayError(message);
    }
}
