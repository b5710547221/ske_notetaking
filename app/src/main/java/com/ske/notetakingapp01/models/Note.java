package com.ske.notetakingapp01.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Note implements Serializable {

    private long createdTimeStamp;
    private String subject;
    private String body;
    private List<String> tags;
    private String colorHex;

    public Note(String subject, String body) {
        this.subject = subject;
        this.body = body;
        this.tags = new ArrayList<String>();
        this.createdTimeStamp = System.currentTimeMillis();
        this.colorHex = "#ffffff";
    }

    public Note(String subject, String body, List<String> tags) {
        this.subject = subject;
        this.body = body;
        this.tags = tags;
        this.createdTimeStamp = System.currentTimeMillis();
        this.colorHex = "#ffffff";
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public List<String> getTags() {
        return tags;
    }

    public long getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    public void setColorHex(String colorHex) {
        this.colorHex = colorHex;
    }

    public String getColorHex() {
        return colorHex;
    }

    public String getReadableCreatedTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy HH:mm");
        Date resultdate = new Date(getCreatedTimeStamp());
        return sdf.format(resultdate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Note)) return false;

        Note note = (Note) o;

        return createdTimeStamp == note.createdTimeStamp;

    }

    @Override
    public int hashCode() {
        return (int) (createdTimeStamp ^ (createdTimeStamp >>> 32));
    }

    public static class AlphabetComparator implements Comparator<Note> {

        @Override
        public int compare(Note n1, Note n2) {
            return n1.getSubject().compareTo(n2.getSubject());
        }
    }

    public static class TimeComparator implements Comparator<Note> {

        @Override
        public int compare(Note n1, Note n2) {
            return (int)(n1.getCreatedTimeStamp() - n2.getCreatedTimeStamp());
        }
    }


}
