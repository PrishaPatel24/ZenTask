package entity;

/**
 * The representation of a note file for this program.
 */
public class Note {

    private final String content;
    private final String title;

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

    public String setTitle(newTitle){
         this.title = newTitle;
    }
}
