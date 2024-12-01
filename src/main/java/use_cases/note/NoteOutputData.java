package use_cases.note;

/**
 * The Output data for the Notes Use Case.
 */
public class NoteOutputData {

    private final String title;
    private final String content;
    private final boolean useCaseFailed;

    public NoteOutputData(String title, String content, boolean useCaseFailed) {
        this.title = title;
        this.content = content;
        this.useCaseFailed = useCaseFailed;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
