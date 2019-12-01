package com.example.notes;

import com.google.firebase.Timestamp;

public class Note {
    private String text;
    private boolean completedTask;
    private Timestamp createdDate;
    private String userId;

    public Note() {

    }

    public Note(String text, boolean completedTask, Timestamp createdDate, String userId) {
        this.text = text;
        this.completedTask = completedTask;
        this.createdDate = createdDate;
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
                "text='" + text + '\'' +
                ", completedTask=" + completedTask +
                ", createdDate=" + createdDate +
                ", userId='" + userId + '\'' +
                '}';
    }
}
