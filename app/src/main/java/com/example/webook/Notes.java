package com.example.webook;

import android.os.Parcel;
import android.os.Parcelable;

public class Notes {
    private String id;
    private String notesTitle;
    private String notesDesc;
    private String timestamp;
    public Notes() {
        //empty constructor needed
    }

    public Notes(String id, String notesTitle, String notesDesc, String timestamp) {
        this.id = id;
        this.notesTitle = notesTitle;
        this.notesDesc = notesDesc;
        this.timestamp = timestamp;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNotesJudul() {
        return notesTitle;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNotesJudul(String notesJudul) {
        this.notesTitle = notesJudul;
    }

    public void setNotesIsi(String notesIsi) {
        this.notesDesc = notesIsi;
    }

    public String getNotesIsi() {
        return notesDesc;
    }

    public String getId() { return id; }
}
