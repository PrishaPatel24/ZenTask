package use_cases.calendar;

import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * The input boundary taking in an event.
 */
public interface CalendarInputBoundary {
    /**
     * Executes the calendar use case.
     * @param email to be executed
     */
    void execute(String email) throws GeneralSecurityException, IOException;
}
