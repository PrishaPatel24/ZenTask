package interface_adapter.note;

import use_cases.note.NoteOutputBoundary;
import use_cases.note.NoteOutputData;

/**
 * The presenter for our Note viewing and editing program.
 */
public class NotePresenter implements NoteOutputBoundary {

    private final NoteViewModel noteViewModel;

    public NotePresenter(NoteViewModel noteViewModel) {
        this.noteViewModel = noteViewModel;
    }

    /**
     * Prepares the success view for the Note related Use Cases.
     *
     * @param result the output data
     */
    @Override
    public void prepareSuccessView(NoteOutputData result) {
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
