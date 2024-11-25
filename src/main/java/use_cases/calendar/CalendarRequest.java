package use_cases.calendar;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;

// keep the unused imports for now, this whole import api stuff for google is so
// finicky that i almost had a shed a tear when i realized all the imports were finally in order

/**
 * A sample request to the calendar.
 */
public class CalendarRequest {
    /**
     * Application name.
     */
    private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";
    /**
     * Global instance of the JSON factory.
     */
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    /**
     * Directory to store authorization tokens for this application.
     */
    private static final String SERVICE_ACCOUNT_KEY_FILE = "fair-ceiling-402704-460d2d4af835.json";

    /**
     * Creates credentials.
     * @return Calendar.
     * @throws GeneralSecurityException exception.
     * @throws IOException exception.
     */
    public static Calendar getCalendarService() throws GeneralSecurityException, IOException {
        // Load the service account key JSON file
        final FileInputStream serviceAccountStream = new FileInputStream(SERVICE_ACCOUNT_KEY_FILE);

        // Build the service account credentials
        final GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccountStream)
                .createScoped(List.of("https://www.googleapis.com/auth/calendar"))
                .createDelegated("jennazhang.jz@gmail.com");

        // REPLACE USER WITH AN EMAIL INPUT FROM LIKE A TEXT FIELD OR STH FROMM A POP UOP @ PRISHA
        // YOU DID THIS BEFORE W UR ADD TASK STUFF
        // MINE IS THERE AS A PLACEHOLDER FOR TESTING

        // Construct the Calendar service object
        return new Calendar.Builder(GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY,
                new HttpCredentialsAdapter(credentials)).setApplicationName(APPLICATION_NAME).build();
    }

    /**
     * Retrieves calendar events, first 10 of them.
     * @return List of events.
     * @throws GeneralSecurityException exception.
     * @throws IOException exception.
     */
    public List<Event> getCalendarEvents() throws GeneralSecurityException, IOException {
        final Calendar service = getCalendarService();

        // List the next 10 events from the primary calendar.
        final DateTime now = new DateTime(System.currentTimeMillis());
        final Events events = service.events().list("calendar-id").setMaxResults(10).setTimeMin(now)
                .setOrderBy("startTime").setSingleEvents(true).execute();
        final List<Event> items = events.getItems();
        if (items.isEmpty()) {
            System.out.println("No upcoming events found.");
        }
        else {
            System.out.println("Upcoming events:");
            for (Event event : items) {
                DateTime start = event.getStart().getDateTime();
                if (start == null) {
                    start = event.getStart().getDate();
                }
                System.out.printf("%s (%s)\n", event.getSummary(), start);
            }
        }
        return items;
    }
