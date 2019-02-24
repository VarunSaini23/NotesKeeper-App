package com.varunsaini.android.noteskeeper.database;

import android.content.Context;
import android.os.AsyncTask;

import com.varunsaini.android.noteskeeper.R;
import com.varunsaini.android.noteskeeper.models.SimpleNote;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {SimpleNote.class},version = 1,exportSchema = false)
public abstract class SimpleNoteDatabase extends RoomDatabase {

    public static SimpleNoteDatabase mInstance;

    public abstract SimpleNoteDao simpleNoteDao();

    public static synchronized SimpleNoteDatabase getInstance(Context context){
        if(mInstance == null){
            mInstance = Room.databaseBuilder(context.getApplicationContext(),SimpleNoteDatabase.class
                                                                                ,"note_keeper").fallbackToDestructiveMigration()
                                                                                    .addCallback(roomCallback).build();
        }
        return mInstance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsyncTask(mInstance).execute();

        }
    };




    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null; }

    @Override
    public void clearAllTables() { }

    private static class PopulateAsyncTask extends AsyncTask<Void,Void,Void>{

        private SimpleNoteDao simpleNoteDao;
        private PopulateAsyncTask(SimpleNoteDatabase db){
            simpleNoteDao = db.simpleNoteDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
//            simpleNoteDao.addANote(new SimpleNote(1,"Sample1","asasa","23-Jan-2019","12:00",1));
//            simpleNoteDao.addANote(new SimpleNote(2,"Sample2","rerrtrgr","33-Jan-2019","42:00",5));
//            simpleNoteDao.addANote(new SimpleNote(3,"Sample3","rjkhgfhgs","33-Jan-2019","42:00",5));
//            simpleNoteDao.addANote(new SimpleNote(4,"Sample3", "saaaaaawddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddsssssssssssssssssssssssssssssssssssssssssssssssss","33-Jan-2019","42:00",5));
//            simpleNoteDao.addANote(new SimpleNote(5,"Sample1","asasa","23-Jan-2019","12:00",1));
//            simpleNoteDao.addANote(new SimpleNote(6,"Sample2","rerrtrgr","33-Jan-2019","42:00",5));
//            simpleNoteDao.addANote(new SimpleNote(7,"Sample3","rjkhgfhgs","33-Jan-2019","42:00",5));
//            simpleNoteDao.addANote(new SimpleNote(8,"Sample3", "saaaaaawddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddsssssssssssssssssssssssssssssssssssssssssssssssss","33-Jan-2019","42:00",5));
//            simpleNoteDao.addANote(new SimpleNote(9,"Sample1","asasa","23-Jan-2019","12:00",1));
//            simpleNoteDao.addANote(new SimpleNote(10,"Sample2","rerrtrgr","33-Jan-2019","42:00",5));
//            simpleNoteDao.addANote(new SimpleNote(11,"Sample3","rjkhgfhgs","33-Jan-2019","42:00",5));
//            simpleNoteDao.addANote(new SimpleNote(12,"Sample3", "saaaaaawddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddsssssssssssssssssssssssssssssssssssssssssssssssss","33-Jan-2019","42:00",5));
//            simpleNoteDao.addANote(new SimpleNote(13,"Sample1","asasa","23-Jan-2019","12:00",1));
//            simpleNoteDao.addANote(new SimpleNote(14,"Sample2","rerrtrgr","33-Jan-2019","42:00",5));
//            simpleNoteDao.addANote(new SimpleNote(15,"Sample3","rjkhgfhgs","33-Jan-2019","42:00",5));
//            simpleNoteDao.addANote(new SimpleNote(16,"Sample3", "saaaaaawddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddsssssssssssssssssssssssssssssssssssssssssssssssss","33-Jan-2019","42:00",5));
//            simpleNoteDao.addANote(new SimpleNote(17,"Sample1","asasa","23-Jan-2019","12:00",1));
//            simpleNoteDao.addANote(new SimpleNote(18,"Sample2","rerrtrgr","33-Jan-2019","42:00",5));
//            simpleNoteDao.addANote(new SimpleNote(19,"Sample3","rjkhgfhgs","33-Jan-2019","42:00",5));
//            simpleNoteDao.addANote(new SimpleNote(20,"Sample3", "saaaaaawddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddsssssssssssssssssssssssssssssssssssssssssssssssss","33-Jan-2019","42:00",5));

            return null;
        }
    }

}
