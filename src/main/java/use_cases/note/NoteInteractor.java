package use_cases.note;

/**
 * The "Use Case Interactor" for our two note-related use cases of refreshing
 * the contents of the note and saving the contents of the note. Since they
 * are closely related, we have combined them here for simplicity.
 */
public class NoteInteractor implements NoteInputBoundary {

    private final NoteOutputBoundary noteOutputBoundary;

    public NoteInteractor(NoteOutputBoundary noteOutputBoundary) {
        this.noteOutputBoundary = noteOutputBoundary;
    }

    @Override
    public void executeSave(String content, String title) {
        this.noteOutputBoundary.prepareSuccessView(content);
    }

    @Override
    public void executeUpload(String content) {
        if (content == null || content.isEmpty()) {
            this.noteOutputBoundary.prepareFailView("Upload failed");
        }
        this.noteOutputBoundary.prepareSuccessView(content);
    }

    @Override
    public void executeClear(String content) {
        this.noteOutputBoundary.prepareSuccessView(" ");
    }
}
