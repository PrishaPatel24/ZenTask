package data_access;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Note;
import use_cases.note.NoteDataAccessInterface;

/**
 * This class implements storage of notes within a single run of the program.
 * This does not persist data between runs of the program.
 */
public class InMemoryNoteDataAccessObject implements NoteDataAccessInterface {
    private final Map<String, String> notes;

    public InMemoryNoteDataAccessObject() {
        this.notes = new HashMap<>();
    }

    @Override
    public void saveNote(String title, String note) {
        this.notes.put(title, note);
    }

    @Override
    public List<String> getNotesSaved() {
        return new ArrayList<>(this.notes.keySet());
    }

    @Override
    public Note getNote(String title) {
        Note note = null;
        for (Map.Entry<String, String> entry : this.notes.entrySet()) {
            if (entry.getKey().equals(title)) {
                note = new Note(entry.getKey(), entry.getValue());
            }
        }
        return note;
    }
}
