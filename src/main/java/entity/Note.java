package entity;

/**
 * The representation of a note file for this program.
 */
public class Note {

    private String content;
    private final String creationDate;

    public Note(String content, String creationDate) {
        this.content = content;
        this.creationDate = creationDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreationDate() {
        return creationDate;
    }
}
