package com.ske.notetakingapp01.util;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ske.notetakingapp01.models.Note;
import org.json.JSONException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * A storage for saving and loading notes
 */
public class Storage {

    private static Storage instance;
    private String DB = "NOTES";
    private SharedPreferences.Editor editor;

    public static Storage getInstance() {
        if(instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    private Storage() {}

    public void saveNote(Context context, Note note) throws JSONException {
        editor =  context.getSharedPreferences(DB, Context.MODE_PRIVATE).edit();
        List<Note> notes = loadNotes(context);
        notes.add(note);
        saveNotesJson(new Gson().toJson(notes));
    }

    public void deleteNote(Context context, Note note) throws JSONException {
        editor =  context.getSharedPreferences(DB, Context.MODE_PRIVATE).edit();
        List<Note> notes = loadNotes(context);
        notes.remove(note);
        saveNotesJson(new Gson().toJson(notes));
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
        editor =  context.getSharedPreferences(DB, Context.MODE_PRIVATE).edit();
        saveNotesJson(new Gson().toJson(new ArrayList<Note>()));
    }

    private void saveNotesJson(String notesJson) {
        editor.putString(DB, notesJson);
        editor.commit();
    }

}
