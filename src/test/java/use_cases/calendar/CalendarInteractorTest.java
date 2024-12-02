package use_cases.calendar;

import org.junit.Test;
import entity.Events;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import static org.junit.Assert.*;


public class CalendarInteractorTest {

    @Test
    public void successTest() throws GeneralSecurityException, IOException {
        CalendarOutputBoundary calendarOutputBoundary = new CalendarOutputBoundary() {
            @Override
            public void prepareSuccessView(List<Events> events) {
                assertNotNull(events);
            }

            @Override
            public void prepareFailureView(String message) {
                fail("The use case is not expected to fail!");
            }

        };
        CalendarInteractor calendarInteractor = new CalendarInteractor(calendarOutputBoundary, new CalendarRequest());
        calendarInteractor.execute("jennazhang.jz@gmail.com");
    }

    @Test
    public void failureInvalidEmailTest() throws GeneralSecurityException, IOException {
        CalendarOutputBoundary calendarOutputBoundary = new CalendarOutputBoundary() {

            @Override
            public void prepareSuccessView(List<Events> events) {
                fail("The use case is not expected to fail!");
            }

            @Override
            public void prepareFailureView(String message) {
                assertEquals("Invalid email format!", message);
            }
        };
        CalendarInteractor calendarInteractor = new CalendarInteractor(calendarOutputBoundary, new CalendarRequest());
        calendarInteractor.execute("csc207@outlook.com");
    }

    @Test
    public void failureEmptyEmailTest() throws GeneralSecurityException, IOException {
        CalendarOutputBoundary calendarOutputBoundary = new CalendarOutputBoundary() {

            @Override
            public void prepareSuccessView(List<Events> events) {
                fail("The use case is not expected to fail!");
            }

            @Override
            public void prepareFailureView(String message) {
                assertEquals("Email is required!", message);
            }
        };
        CalendarInteractor calendarInteractor = new CalendarInteractor(calendarOutputBoundary, new CalendarRequest());
        calendarInteractor.execute("");
    }
}

