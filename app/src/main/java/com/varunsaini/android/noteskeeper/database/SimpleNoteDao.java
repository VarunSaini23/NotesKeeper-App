package com.varunsaini.android.noteskeeper.database;

//import android.arch.lifecycle.LiveData;

import com.varunsaini.android.noteskeeper.models.SimpleNote;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface SimpleNoteDao {

    @Insert
    void addANote(SimpleNote simpleNote);

    @Update
    void updateANote(SimpleNote simpleNote);

    @Delete
    void deleteANote(SimpleNote simpleNote);

    @Query("SELECT * FROM simple_note ORDER BY id DESC")
    LiveData<List<SimpleNote>> getAllNotes();

    @Query("SELECT * FROM simple_note where id = :id")
    SimpleNote getSingleNote( int id );
}
