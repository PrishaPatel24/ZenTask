package use_cases.note;

/**
 * The Output data for the Notes Use Case.
 */
public class NoteOutputData {

    private final String content;
    private final boolean useCaseFailed;

    public NoteOutputData(String title, boolean useCaseFailed) {
        this.content = title;
        this.useCaseFailed = useCaseFailed;
    }

    public String getContent() {
        return content;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
