package use_cases.note;

import java.util.List;

import entity.Note;

/**
 * Interface for the NoteDAO. It consists of methods for
 * both loading and saving a note.
 */
public interface NoteDataAccessInterface {

    /**
     * Saves a note.
     * @param title the title associated with the note.
     * @param note the note to be saved.
     */
    void saveNote(String title, String note);

    /**
     * Creates an "in-memory" database of all notes saved in the program.
     * @return a list of the titles of all notes that have been saved.
     */
    List<String> getNotes();
}
