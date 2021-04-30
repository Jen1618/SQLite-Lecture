package com.example.sqlitelectureexample;

public class User {

    // differences between the different types of integers
    // long
    private long id; // object type of long

    private String name;
    private String phone;

    public User(String name, String phone){
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Room database
    // a library that sits on top of your SQLite database

    public String toString(){
        // Celia: 12131
        return this.name + ": " + this.phone + " (" + this.id + ")";
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // kotlin
    // first week -> basic syntax, basic views
    // 1) multi-tasking
    // 2) Room with LiveData
    // 3) Testing
    // 4) If we have time, login process
}
