package use_cases.savenote;

/**
 * The Output data for the Notes Use Case.
 */
public class SaveNoteOutputData {

    private final String title;
    private final String content;

    public SaveNoteOutputData(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

}
