package com.example.webook;

public class UserData {
    private String title, notes, link, toDo, desc;

    public UserData() {
        //empty constructor needed
    }

    public UserData(String notes, String title, String desc) {
        this.title = title;
        this.notes = notes;
        this.desc = desc;
    }


    public String getTitle() {
        return title;
    }

    public String getNotes() {
        return notes;
    }
}
