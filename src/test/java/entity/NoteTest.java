package entity;

import org.junit.Test;

public class NoteTest {
    @Test
    public void getContentTest() {
        final Note testNote = new Note("content of note is here", "2024-11-18");
        assert testNote.getContent().equals("content of note is here");
    }

    @Test
    public void setContentTest() {
        final Note testNote = new Note("content of note is here", "2024-11-18");
        testNote.setContent("new note content");
        assert testNote.getContent().equals("new note content");
    }

    @Test
    public void getDateTest() {
        final Note testNote = new Note("content of note is here", "2024-11-18");
        assert testNote.getTitle().equals("2024-11-18");
    }
}
