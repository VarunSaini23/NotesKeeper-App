package com.varunsaini.android.noteskeeper.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "simple_note")
public class SimpleNote {


    @PrimaryKey
    private int id;
    private String title;
    private String content;
    private String date;
    private String time;
    private int theme;


    public SimpleNote(int id, String title, String content, String date, String time, int theme) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.time = time;
        this.theme = theme;
    }

    //////////////////all the getters/////////////////////////
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getTheme() {
        return theme;
    }

    //////////////////all the setters/////////////////////////

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTheme(int theme) {
        this.theme = theme;
    }
}
