package com.varunsaini.android.noteskeeper.repository;

import android.app.Application;
//import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.varunsaini.android.noteskeeper.database.SimpleNoteDao;
import com.varunsaini.android.noteskeeper.database.SimpleNoteDatabase;
import com.varunsaini.android.noteskeeper.models.SimpleNote;

import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.lifecycle.LiveData;

public class SimpleNoteRepository {

    private SimpleNoteDao simpleNoteDao;
    private LiveData<List<SimpleNote>> listLiveAllNotesData ;
    private SimpleNote mSimpleNote ;

    public SimpleNoteRepository(Application application){

        SimpleNoteDatabase simpleNoteDatabase = SimpleNoteDatabase.getInstance(application);
        simpleNoteDao = simpleNoteDatabase.simpleNoteDao();
        listLiveAllNotesData = simpleNoteDao.getAllNotes();

    }

    public void insertSimpleNote(SimpleNote simpleNote){
            new InsertAsyncTask(simpleNoteDao).execute(simpleNote);
    }

    public void updateSimpleNote(SimpleNote simpleNote){
        new UpdateAsyncTask(simpleNoteDao).execute(simpleNote);
    }

    public void deleteSimpleNote(SimpleNote simpleNote){
        new DeleteAsyncTask(simpleNoteDao).execute(simpleNote);
    }

    public LiveData<List<SimpleNote>> getAllNotes(){
        return listLiveAllNotesData;
    }

    public SimpleNote getSingleNote(int id) throws ExecutionException, InterruptedException {
        return new GetSingleNoteAsyncTask(simpleNoteDao).execute(id).get();
    }


    public static class InsertAsyncTask extends AsyncTask<SimpleNote,Void,Void>{

        private SimpleNoteDao mSimpleNoteDao;
        public InsertAsyncTask(SimpleNoteDao simpleNoteDao){
            mSimpleNoteDao = simpleNoteDao;
        }
        @Override
        protected Void doInBackground(SimpleNote... simpleNotes) {
            mSimpleNoteDao.addANote(simpleNotes[0]);
            return null;
        }
    }

    public static class UpdateAsyncTask extends AsyncTask<SimpleNote,Void,Void>{

        private SimpleNoteDao mSimpleNoteDao;
        public UpdateAsyncTask(SimpleNoteDao simpleNoteDao){
            mSimpleNoteDao = simpleNoteDao;
        }
        @Override
        protected Void doInBackground(SimpleNote... simpleNotes) {
            mSimpleNoteDao.updateANote(simpleNotes[0]);
            return null;
        }
    }

    public static class DeleteAsyncTask extends AsyncTask<SimpleNote,Void,Void>{

        private SimpleNoteDao mSimpleNoteDao;
        public DeleteAsyncTask(SimpleNoteDao simpleNoteDao){
            mSimpleNoteDao = simpleNoteDao;
        }
        @Override
        protected Void doInBackground(SimpleNote... simpleNotes) {
            mSimpleNoteDao.deleteANote(simpleNotes[0]);
            return null;
        }
    }

    public class GetSingleNoteAsyncTask extends AsyncTask<Integer ,Void,SimpleNote>{

        private SimpleNoteDao  mSimpleNoteDao;
        public GetSingleNoteAsyncTask(SimpleNoteDao simpleNoteDao) {
            mSimpleNoteDao =  simpleNoteDao;
        }

        @Override
        protected void onPostExecute(SimpleNote simpleNote) {
            super.onPostExecute(simpleNote);
            mSimpleNote = simpleNote;

        }

        @Override
        protected SimpleNote doInBackground(Integer... integers) {
            return simpleNoteDao.getSingleNote(integers[0]);
        }
    }
}
