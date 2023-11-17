package com.example.myapplication_notepad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class NoteArrayAdapter extends BaseAdapter {
    private final List<Note> notes;
    private final LayoutInflater inflater;

    public NoteArrayAdapter(Context context, List<Note> notes) {
        this.notes = notes;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Note getItem(int position) {
        return notes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }

        TextView titleTextView = convertView.findViewById(R.id.titleTextView);

        Note note = getItem(position);

        titleTextView.setText(note.getTitle());

        return convertView;
    }
}
