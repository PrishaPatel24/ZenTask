package use_cases.calendar;

import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * The input boundary taking in an event.
 */
public interface CalendarInputBoundary {
    /**
     * This checks the validity of the email and requests for the user's Google Calendar if the email is valid.
     * @param email String of the user's email address that is used to retrieve the Google calendar.
     * @throws GeneralSecurityException is thrown if there was an issue with logging into the user's Google account.
     * @throws IOException is thrown if there was an issue getting the events information from the Google Calendar
     *         and displaying it to the user.
     */
    void execute(String email) throws GeneralSecurityException, IOException;
}
