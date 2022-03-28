package com.example.IDATT2015QS3REST.model;

public class User {
    private int userID;
    private String username;
    private String passw;
    private String role;


    public User(int userID, String username, String passw, String role){
        this.userID = userID;
        this.username = username;
        this.passw = passw;
        this.role = role;
    }
    public User() {

    }

    public User(String username, String passw, String role) {
        this.username = username;
        this.passw = passw;
        this.role = role;
    }

    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassw() {
        return passw;
    }

    public String getRole() {
        return role;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

