package app;

import interface_adapter.note.NoteViewModel;
import use_cases.note.NoteDataAccessInterface;
import use_cases.note.NoteInteractor;
import view.NoteView;

public class AppBuilder {
    public static final int HEIGHT = 300;
    public static final int WIDTH = 400;
    private NoteView noteView;
    private NoteInteractor noteInteractor;
}
