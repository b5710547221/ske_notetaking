package com.ske.notetakingapp01.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ske.notetakingapp01.models.Note;

import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private static Storage instance;

    public static Storage getInstance() {
        if(instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    private Storage() {}

    private String DB = "NOTES";

    public void saveNote(Context context, Note note) throws JSONException {
        SharedPreferences.Editor editor =  context.getSharedPreferences(DB, Context.MODE_PRIVATE).edit();
        List<Note> notes = loadNotes(context);
        notes.add(note);
        String notesJson = new Gson().toJson(notes);
        Log.d("NoteApp", "Save json string: " + notesJson);
        editor.putString(DB, notesJson);
        editor.commit();
    }

    public void deleteNote(Context context, Note note) throws JSONException {
        SharedPreferences.Editor editor =  context.getSharedPreferences(DB, Context.MODE_PRIVATE).edit();
        List<Note> notes = loadNotes(context);
        notes.remove(note);
        String notesJson = new Gson().toJson(notes);
        Log.d("NoteApp", "Save json string: " + notesJson);
        editor.putString(DB, notesJson);
        editor.commit();
    }

    public List<Note> loadNotes(Context context) throws JSONException {
        String notesJson = context.getSharedPreferences(DB, Context.MODE_PRIVATE).getString(DB, null);
        if(notesJson == null || notesJson.trim().equals("")) {
            return new ArrayList<Note>();
        }
        Type type = new TypeToken< List < Note >>() {}.getType();
        return new Gson().fromJson(notesJson, type);
    }

    public void clear(Context context) {
        SharedPreferences.Editor editor =  context.getSharedPreferences(DB, Context.MODE_PRIVATE).edit();
        List<Note> notes = new ArrayList<Note>();
        String notesJson = new Gson().toJson(notes);
        editor.putString(DB, notesJson);
        editor.commit();
    }

}
