package use_cases.note;

/**
 * The Output data for the Notes Use Case.
 */
public class NoteOutputData {

    private final String title;
    private final boolean useCaseFailed;

    public NoteOutputData(String title, boolean useCaseFailed) {
        this.title = title;
        this.useCaseFailed = useCaseFailed;
    }

    public String getTitle() {
        return title;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
