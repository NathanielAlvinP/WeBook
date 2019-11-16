package com.example.webook;

import com.google.firebase.firestore.PropertyName;

public class UserData {
    private String titleNote, desc;

    public UserData() {
        //empty constructor needed
    }

    public UserData(String titleNote, String desc) {
        this.titleNote = titleNote;
        this.desc = desc;
    }

    @PropertyName("title")
    public String getTitleNote() {
        return titleNote;
    }

    @PropertyName("description")
    public String getDesc() {
        return desc;
    }
}
