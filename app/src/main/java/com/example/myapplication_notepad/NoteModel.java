package com.example.myapplication_notepad;

import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NoteModel {
    private final SharedPreferences sharedPreferences;
    private final List<Note> notes;

    public NoteModel(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        notes = new ArrayList<>();
        loadNotes();
    }

    public List<String> getTitles() {
        List<String> titles = new ArrayList<>();
        for (Note note : notes) {
            titles.add(note.getTitle());
        }
        return titles;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void addOrUpdateNote(String title, String text, int color, int position) {
        if (position == -1) {
            notes.add(new Note(title, text, color));
        } else {
            Note existingNote = notes.get(position);
            existingNote.setTitle(title);
            existingNote.setNoteText(text);
            existingNote.setColor(color);
        }
        saveNotesToSharedPreferences();
    }

    private void loadNotes() {
        Set<String> noteSet = sharedPreferences.getStringSet("notes", new HashSet<>());
        notes.clear();
        for (String noteString : noteSet) {
            String[] parts = noteString.split(":");
            if (parts.length == 3) {
                String title = parts[0];
                String text = parts[1];
                int color = Integer.parseInt(parts[2]);
                notes.add(new Note(title, text, color));
            }
        }
    }

    private void saveNotesToSharedPreferences() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> noteSet = new HashSet<>();
        for (Note note : notes) {
            String noteString = note.getTitle() + ":" + note.getNoteText() + ":" + note.getColor();
            noteSet.add(noteString);
        }
        editor.putStringSet("notes", noteSet);
        editor.apply();
    }
}
