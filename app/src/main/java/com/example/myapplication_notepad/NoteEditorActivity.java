package com.example.myapplication_notepad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class NoteEditorActivity extends AppCompatActivity {
    private EditText noteTitleEditText;
    private int notePosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        noteTitleEditText = findViewById(R.id.note_Title);
        Button saveNoteButton = findViewById(R.id.saveNoteButton);
        Button deleteNoteButton = findViewById(R.id.deleteNoteButton);
        Button cancelNoteButton = findViewById(R.id.cancelNoteButton);

        Intent intent = getIntent();
        if (intent.hasExtra("noteTitle")) {
            String noteTitle = intent.getStringExtra("noteTitle");
            notePosition = intent.getIntExtra("notePosition", -1);
            noteTitleEditText.setText(noteTitle);
        }

        saveNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });

        deleteNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteNote();
            }
        });

        cancelNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelNote();
            }
        });
    }

    private void saveNote() {
        String updatedTitle = noteTitleEditText.getText().toString();

        Intent resultIntent = new Intent();
        resultIntent.putExtra("noteTitle", updatedTitle);
        resultIntent.putExtra("notePosition", notePosition);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    private void deleteNote() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("noteTitle", "");
        resultIntent.putExtra("notePosition", notePosition);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    private void cancelNote() {
        finish();
    }
}
