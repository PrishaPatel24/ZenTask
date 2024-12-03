package interface_adapter.calendar;

import java.io.IOException;
import java.security.GeneralSecurityException;

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
     * @param email the email of user.
     * @throws GeneralSecurityException for Google account.
     * @throws IOException for Google login.
     */
    public void execute(String email) throws GeneralSecurityException, IOException {
        calendarUseCaseInteractor.execute(email);
    }
}
