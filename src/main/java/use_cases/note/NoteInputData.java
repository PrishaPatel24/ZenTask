package use_cases.note;

/**
 * The input data for the Notes Use Case.
 */
public class NoteInputData {
    private final String title;
    private final String content;

    public NoteInputData(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getNo() {
        return content;
    }
}
