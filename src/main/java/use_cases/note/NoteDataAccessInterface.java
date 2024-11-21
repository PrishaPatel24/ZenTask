package use_cases.note;

import entity.Note;

/**
 * Interface for the NoteDAO. It consists of methods for
 * both loading and saving a note.
 */
public interface NoteDataAccessInterface {

    /**
     * Saves a note.
     * <p>The password of the user must match that of the user saved in the system.</p>
     * @param title the title associated with the note
     * @param note the note to be saved
     */
    void saveNote(String title, Note note);

    /**
     * Returns the note associated with the title.
     * @param title the title associated with the note
     * @return the contents of the note
     */
    String loadNote(String title);

}
