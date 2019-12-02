package com.example.notes;

import android.media.Image;

import com.google.firebase.Timestamp;

public class Note {
    private String Judul;
    private String Isi;
    private boolean completedTask;
    private Timestamp createdDate;
    private String userId;
    private String imgURL;
    public Note() {

    }

    public Note(String judul, String isi, boolean completedTask, Timestamp createdDate, String userId) {
        this.Judul = judul;
        this.setIsi(isi);
        this.completedTask = completedTask;
        this.createdDate = createdDate;
        this.userId = userId;
    }

    public String getJudul() {
        return Judul;
    }

    public void setJudul(String text) {
        this.Judul = text;
    }

    public boolean isCompletedTask() {
        return completedTask;
    }

    public void setCompletedTask(boolean completedTask) {
        this.completedTask = completedTask;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Note{" +
                "text='" + Judul + '\'' +
                ", completedTask=" + completedTask +
                ", createdDate=" + createdDate +
                ", userId='" + userId + '\'' +
                ", Image='"+imgURL+ '\''+
                '}';
    }

    public String getIsi() {
        return Isi;
    }

    public void setIsi(String isi) {
        Isi = isi;
    }

    public void setImage(String img){
        this.imgURL = img;
    }
    public String getImage(){
        return imgURL;
    }
}
