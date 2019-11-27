package com.example.webook;

public class Notes {
    String id;
    String notesTitle;
    String notesDesc;
    String notesTodo;

    public Notes() {
        //empty constructor needed
    }

    public Notes(String id, String notesTitle, String notesDesc) {
        this.id = id;
        this.notesTitle = notesTitle;
        this.notesDesc = notesDesc;
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

    public void setNotesTodo(String notesTodo) {
        this.notesTodo = notesTodo;
    }

    public String getNotesIsi() {
        return notesDesc;
    }

    public String getNotesTodo() {
        return notesTodo;
    }
}
