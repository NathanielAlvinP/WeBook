package com.example.webook;

public class UserData {
    private String notes, link, toDo;

    public UserData() {
        //empty constructor needed
    }

    public UserData(String notes, String link, String toDo) {
        this.notes = notes;
        this.link = link;
        this.toDo = toDo;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getToDo() {
        return toDo;
    }

    public void setToDo(String toDo) {
        this.toDo = toDo;
    }
}
