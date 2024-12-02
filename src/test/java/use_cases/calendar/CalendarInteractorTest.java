package use_cases.calendar;

import entity.Note;
import org.junit.Test;
import entity.Events;
import use_cases.ai.AiInteractor;
import use_cases.ai.AiOutputBoundary;
import use_cases.ai.AiRequest;

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
        };
        CalendarInteractor calendarInteractor = new CalendarInteractor(calendarOutputBoundary, new CalendarRequest());
        calendarInteractor.execute("jennazhang.jz@gmail.com");
    }

}
