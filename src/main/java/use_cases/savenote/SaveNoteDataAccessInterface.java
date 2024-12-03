package use_cases.savenote;

import java.util.List;

import entity.Note;

/**
 * Interface for the NoteDAO. It consists of methods for
 * both loading and saving a note.
 */
public interface SaveNoteDataAccessInterface {

    /**
     * Saves a note.
     * @param title the title associated with the note.
     * @param note the note to be saved.
     */
    void saveNote(String title, String note);

    /**
     * This will be used to check that no two notes have the same title.
     * @return A list of saved notes.
     */
    List<String> getNotesSaved();

    /**
     * This returns the note as an object that has been stored in memory.
     * @param title this is used as a unique identifier to retrieve a note.
     * @return note if it is saved in memory.
     */
    Note getNote(String title);
}
