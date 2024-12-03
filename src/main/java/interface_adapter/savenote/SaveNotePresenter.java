package interface_adapter.savenote;

import use_cases.savenote.SaveNoteOutputBoundary;
import use_cases.savenote.SaveNoteOutputData;

/**
 * The presenter for our Note viewing and editing program.
 */
public class SaveNotePresenter implements SaveNoteOutputBoundary {

    private final NoteViewModel noteViewModel;

    public SaveNotePresenter(NoteViewModel noteViewModel) {
        this.noteViewModel = noteViewModel;
    }

    /**
     * Prepares the success view for the Note related Use Cases.
     *
     * @param result the output data
     */
    @Override
    public void prepareSuccessView(SaveNoteOutputData result) {
        noteViewModel.getState().setNote(result.getContent());
        noteViewModel.getState().setTitle(result.getTitle());
        noteViewModel.getState().setError(null);
        noteViewModel.firePropertyChanged();
    }

    /**
     * Prepares the failure view for the Note related Use Cases.
     *
     * @param errorMessage the explanation of the failure
     */
    @Override
    public void prepareFailView(String errorMessage) {
        noteViewModel.getState().setError(errorMessage);
        noteViewModel.firePropertyChanged();
    }
}
