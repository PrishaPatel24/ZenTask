package data_access;

import java.util.Map;

import entity.Note;
import use_cases.note.NoteDataAccessInterface;

/**
 * This class implements storage of notes within a single run of the program.
 * This does not persist data between runs of the program.
 */
public class InMemoryNoteDataAccessObject implements NoteDataAccessInterface {
    private final Map<String, Note> notes;

    public InMemoryNoteDataAccessObject(Map<String, Note> notes) {
        this.notes = notes;
    }

    @Override
    public void saveNote(String title, Note note) {
        this.notes.put(title, note);
    }

    @Override
    public String loadNote(String title) {
        final Note note = this.notes.get(title);
        return note.getContent();
    }

    public String[] getNotes() {
        return this.notes.keySet().toArray(new String[0]);
    }
}
