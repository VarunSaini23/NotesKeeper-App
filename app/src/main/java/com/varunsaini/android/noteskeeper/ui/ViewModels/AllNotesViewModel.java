package com.varunsaini.android.noteskeeper.ui.ViewModels;

import android.app.Application;

import com.varunsaini.android.noteskeeper.models.SimpleNote;
import com.varunsaini.android.noteskeeper.repository.SimpleNoteRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class AllNotesViewModel extends AndroidViewModel {

    private SimpleNoteRepository simpleNoteRepository;
    private LiveData<List<SimpleNote>> listSimpleNoteLiveData;
    private AllNotesViewModel allNotesViewModel;

    public AllNotesViewModel(@NonNull Application application) {
        super(application);
        simpleNoteRepository = new SimpleNoteRepository(application);
        listSimpleNoteLiveData = simpleNoteRepository.getAllNotes();
    }



    public void insertNote(SimpleNote simpleNote){
        simpleNoteRepository.insertSimpleNote(simpleNote);
    }

    public void updateNote(SimpleNote simpleNote){
        simpleNoteRepository.updateSimpleNote(simpleNote);
    }

    public void deleteNote(SimpleNote simpleNote){
        simpleNoteRepository.deleteSimpleNote(simpleNote);
    }

    public LiveData<List<SimpleNote>> getListSimpleNoteLiveData() {
        return listSimpleNoteLiveData;
    }



}
