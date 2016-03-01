package com.ske.notetakingapp01.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * A simple note
 */
public class Note implements Serializable {

    public static final String DEFAULT_COLOR = "#ffffff";

    private long createdTimeStamp;
    private String subject;
    private String body;
    private List<String> tags;
    private String colorHex;

    public Note(String subject, String body, List<String> tags) {
        this.subject = subject;
        this.body = body;
        this.tags = tags;
        this.createdTimeStamp = System.currentTimeMillis();
        this.colorHex = DEFAULT_COLOR;
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

    public String getColorHex() {
        return colorHex;
    }

    public String getReadableCreatedTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy - HH:mm");
        Date resultdate = new Date(getCreatedTimeStamp());
        return sdf.format(resultdate);
    }

    public void setColorHex(String colorHex) {
        this.colorHex = colorHex;
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
