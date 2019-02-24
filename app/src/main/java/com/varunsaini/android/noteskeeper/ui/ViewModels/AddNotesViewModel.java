package com.varunsaini.android.noteskeeper.ui.ViewModels;

import android.app.Application;

import com.varunsaini.android.noteskeeper.database.SimpleNoteDao;
import com.varunsaini.android.noteskeeper.models.SimpleNote;
import com.varunsaini.android.noteskeeper.repository.SimpleNoteRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class AddNotesViewModel extends AndroidViewModel {

    private SimpleNoteRepository simpleNoteRepository;
    private LiveData<List<SimpleNote>> listSimpleNoteLiveData;
    private String noteContent;

    public AddNotesViewModel(@NonNull Application application) {
        super(application);
        simpleNoteRepository = new SimpleNoteRepository(application);
    }

    public void insertNote(SimpleNote note){
        simpleNoteRepository.insertSimpleNote(note);
    }

    public SimpleNote getSingleNote(int id) throws ExecutionException, InterruptedException {
        return simpleNoteRepository.getSingleNote(id);
    }

}
