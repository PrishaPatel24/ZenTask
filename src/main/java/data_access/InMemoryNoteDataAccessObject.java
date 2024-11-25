package data_access;

import java.util.HashMap;
import java.util.Map;

import entity.Note;
import use_cases.note.NoteDataAccessInterface;

/**
 * This class implements storage of notes within a single run of the program.
 * This does not persist data between runs of the program.
 */
public class InMemoryNoteDataAccessObject implements NoteDataAccessInterface {
    private final Map<String, Note> notes;

    public InMemoryNoteDataAccessObject() {
        this.notes = new HashMap<>();
    }

    @Override
    public void saveNote(String title, Note note) {
        this.notes.put(title, note);
    }

    public String[] getNotes() {
        return this.notes.keySet().toArray(new String[0]);
    }
}
