package com.ske.notetakingapp01.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ske.notetakingapp01.R;
import com.ske.notetakingapp01.models.Note;

import org.w3c.dom.Text;

import java.util.List;

public class NotesAdapter extends ArrayAdapter<Note> {

    public NotesAdapter(Context context, int resource, List<Note> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if(v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.note_grid, null);
        }

        TextView body = (TextView) v.findViewById(R.id.body);
        TextView subject = (TextView) v.findViewById(R.id.subject);
        TextView createdTime = (TextView) v.findViewById(R.id.created_time);
        TextView tags = (TextView) v.findViewById(R.id.tags);

        subject.setText(getItem(position).getSubject());
        body.setText(getItem(position).getBody());
        createdTime.setText(getItem(position).getReadableCreatedTime());

        String delim = "";
        String tagString = "";
        for(String s : getItem(position).getTags()) {
            tagString += (delim + " " + s);
            delim = ",";
        }
        tags.setText(tagString);

        v.setBackgroundColor(Color.parseColor(getItem(position).getColorHex()));

        return v;
    }
}
