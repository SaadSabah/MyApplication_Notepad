package com.example.myapplication_notepad;

public class Note {

    private String title;
    private String noteText;
    private int color;

    public Note(String title, String noteText, int color) {
        this.title = title;
        this.noteText = noteText;
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public String getNoteText() {
        return noteText;
    }

    public int getColor() {
        return color;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public void setColor(int color) {
        this.color = color;
    }


}
