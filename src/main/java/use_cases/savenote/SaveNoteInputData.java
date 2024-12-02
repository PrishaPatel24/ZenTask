package use_cases.savenote;

/**
 * The input data for the Notes Use Case.
 */
public class SaveNoteInputData {
    private final String title;
    private final String content;

    public SaveNoteInputData(String title, String content) {
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
