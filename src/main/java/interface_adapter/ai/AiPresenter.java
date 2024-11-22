package interface_adapter.ai;

import entity.Note;
import use_cases.ai.AiOutputBoundary;
import view.NotesView;

/**
 * The stupid AiPresenter for this use case, intended to update the view.
 */
public class AiPresenter implements AiOutputBoundary {
    private final NotesView notesView;

    public AiPresenter(NotesView notesView) {
        this.notesView = notesView;
    }

    /**
     * Tasked with updating the view of the program with the response.
     * @param note The final output from the AI.
     */
    public void updateNote(Note note) {
        notesView.displayNewNote(note);
    }
}
