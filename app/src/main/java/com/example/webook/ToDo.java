package com.example.webook;

public class ToDo {
    String id;
    String todo;

    public ToDo(){

    }
    public ToDo(String id, String todo){
        this.id = id;
        this.todo = todo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getId() {
        return id;
    }

    public String getTodo() {
        return todo;
    }
}
