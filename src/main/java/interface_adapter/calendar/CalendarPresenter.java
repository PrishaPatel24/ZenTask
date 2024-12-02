package interface_adapter.calendar;

import entity.Events;
import use_cases.calendar.CalendarOutputBoundary;
import view.CalendarView;

import java.util.List;

/**
 * The presenter for the calendar use case.
 */
public class CalendarPresenter implements CalendarOutputBoundary {
    private CalendarView calendarView;

    public CalendarPresenter(CalendarView calendarView) {
        this.calendarView = calendarView;
    }

    public void prepareSuccessView(List<Events> events) {
        calendarView.displayEvents(events);
    }
}
