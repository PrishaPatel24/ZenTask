package use_cases.savenote;

/**
 * The output boundary for the Login Use Case.
 */
public interface SaveNoteOutputBoundary {
    /**
     * Prepares the success view for the Note related Use Cases.
     * @param result the output data
     */
    void prepareSuccessView(SaveNoteOutputData result);

    /**
     * Prepares the failure view for the Note related Use Cases.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
