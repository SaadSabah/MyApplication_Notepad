package com.example.myapplication_notepad;

                                                   //MVC
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private NoteArrayAdapter arrayAdapter;
    private static final int REQUEST_CODE_NOTE_EDITOR = 1;
    private NoteModel noteModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView noteListView = findViewById(R.id.noteListView);
        Button addNoteButton = findViewById(R.id.addNoteButton);

        noteModel = new NoteModel(getSharedPreferences("MyNotes", MODE_PRIVATE));

        arrayAdapter = new NoteArrayAdapter(this, noteModel.getNotes());
        noteListView.setAdapter((ListAdapter) arrayAdapter);

        noteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Note selectedNote = noteModel.getNotes().get(position);
                Intent intent = new Intent(getApplicationContext(), NoteEditorActivity.class);
                intent.putExtra("noteTitle", selectedNote.getTitle());
                intent.putExtra("noteText", selectedNote.getNoteText());
                intent.putExtra("noteColor", selectedNote.getColor());
                intent.putExtra("notePosition", position);
                startActivityForResult(intent, REQUEST_CODE_NOTE_EDITOR);
            }
        });

        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NoteEditorActivity.class);
                intent.putExtra("noteTitle", "");
                intent.putExtra("noteText", "");
                intent.putExtra("noteColor", -1); // Default color
                intent.putExtra("notePosition", -1);
                startActivityForResult(intent, REQUEST_CODE_NOTE_EDITOR);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_NOTE_EDITOR) {
            if (resultCode == RESULT_OK && data != null) {
                String updatedTitle = data.getStringExtra("noteTitle");
                String updatedText = data.getStringExtra("noteText");
                int updatedColor = data.getIntExtra("noteColor", -1);
                int position = data.getIntExtra("notePosition", -1);
                noteModel.addOrUpdateNote(updatedTitle, updatedText, updatedColor, position);
                arrayAdapter.notifyDataSetChanged();
            }
        }
    }
}
