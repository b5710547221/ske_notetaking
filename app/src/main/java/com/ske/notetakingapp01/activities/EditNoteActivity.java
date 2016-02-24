package com.ske.notetakingapp01.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ske.notetakingapp01.R;
import com.ske.notetakingapp01.models.Note;
import com.ske.notetakingapp01.util.Storage;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class EditNoteActivity extends AppCompatActivity {

    private TextView subject;
    private TextView body;
    private TextView tags;
    private TextView colorHex;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        initComponents();
    }

    private void initComponents() {
        subject = (TextView) findViewById(R.id.subject);
        body = (TextView) findViewById(R.id.body);
        tags = (TextView) findViewById(R.id.tags);
        colorHex = (TextView) findViewById(R.id.color);
        saveButton = (Button) findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
                finish();
            }
        });
    }

    private void save() {
        String [] tagArray = tags.getText().toString().split(",");
        List<String> tagList = new ArrayList<String>();
        for(int i = 0; i < tagArray.length; i++) {
            tagList.add(tagArray[i].trim());
        }
        Note note = new Note(subject.getText().toString(), body.getText().toString(), tagList);
        note.setColorHex(colorHex.getText().toString());
        try {
            Storage.getInstance().saveNote(this, note);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
