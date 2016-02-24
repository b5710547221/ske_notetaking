package com.ske.notetakingapp01.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ske.notetakingapp01.R;
import com.ske.notetakingapp01.models.Note;
import com.ske.notetakingapp01.util.Storage;

import org.json.JSONException;

public class CardActivity extends AppCompatActivity {

    private TextView subject;
    private TextView body;
    private Button deleteButton;
    private Button shareButton;

    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        Intent intent = getIntent();
        note = (Note)intent.getSerializableExtra("note");
        initComponents();
    }

    private void initComponents() {
        subject = (TextView) findViewById(R.id.subject);
        body = (TextView) findViewById(R.id.body);
        subject.setText(note.getSubject());
        body.setText(note.getBody());
        deleteButton = (Button) findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteThisNote();
                finish();
            }
        });
        shareButton = (Button) findViewById(R.id.share_button);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareNote();
            }
        });
    }

    private void shareNote() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, note.getSubject() + "\n" + note.getBody());
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    private void deleteThisNote() {
        try {
            Storage.getInstance().deleteNote(this, note);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
