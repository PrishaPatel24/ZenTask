package entity;

/**
 * The representation of a note file for this program.
 */
public class Note {

    private String content;
    private String title;

    public Note(String content, String title) {
        this.content = content;
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }
  
    public void setContent(String newContent) {
        this.content = newContent;
    }

    public void setTitle(String newTitle) {
         title = newTitle;
    }

}
