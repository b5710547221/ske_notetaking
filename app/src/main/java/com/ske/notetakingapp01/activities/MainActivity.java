package com.ske.notetakingapp01.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ske.notetakingapp01.R;
import com.ske.notetakingapp01.adapters.NotesAdapter;
import com.ske.notetakingapp01.models.Note;
import com.ske.notetakingapp01.util.Storage;

import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Note> notes;
    private GridView notesGrid;
    private NotesAdapter notesAdapter;

    private Button newButton;
    private Button deleteAllButton;
    private Button sortAZButton;
    private Button sortTimeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadNotes();
    }

    private void initComponents() {
        notesGrid = (GridView)findViewById(R.id.notes_grid);
        notes = new ArrayList<Note>();
        notesAdapter = new NotesAdapter(this, R.layout.note_grid, notes);
        notesGrid.setAdapter(notesAdapter);
        notesGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, CardActivity.class);
                intent.putExtra("note", notes.get(i));
                startActivity(intent);
            }
        });
        newButton = (Button) findViewById(R.id.new_button);
        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditNoteActivity.class);
                startActivity(intent);
            }
        });
        deleteAllButton = (Button) findViewById(R.id.delete_all_button);
        deleteAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Storage.getInstance().clear(MainActivity.this);
                loadNotes();
            }
        });
        sortAZButton = (Button) findViewById(R.id.sort_az);
        sortAZButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(notes, new Note.AlphabetComparator());
                notesAdapter.notifyDataSetChanged();
            }
        });
        sortTimeButton = (Button) findViewById(R.id.sort_time);
        sortTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(notes, new Note.TimeComparator());
                notesAdapter.notifyDataSetChanged();
            }
        });
    }

    private void loadNotes() {
        try {
            notes.clear();
            for(Note n: Storage.getInstance().loadNotes(this)) {
                notes.add(n);
            }
            notesAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
