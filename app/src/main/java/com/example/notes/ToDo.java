package com.example.notes;

import android.widget.CheckBox;

import com.google.firebase.Timestamp;

public class ToDo {
    private String text;
    private boolean completedTask;
    private Timestamp createdDate;
    private String userId;
    private CheckBox checkBox;

    public ToDo(){
        //empty constructor needed
    }

    public ToDo(String text, boolean completedTask, Timestamp createdDate, String userId, CheckBox checkBox) {
        this.text = text;
        this.completedTask = completedTask;
        this.createdDate = createdDate;
        this.userId = userId;
        this.checkBox = checkBox;
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

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    @Override
    public String toString() {
        return "ToDo{" +
                "text='" + text + '\'' +
                ", completedTask=" + completedTask +
                ", createdDate=" + createdDate +
                ", userId='" + userId + '\'' +
                ", checkBox=" + checkBox +
                '}';
    }
}
