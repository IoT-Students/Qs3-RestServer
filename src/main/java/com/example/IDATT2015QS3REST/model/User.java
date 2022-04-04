package com.example.IDATT2015QS3REST.model;

public class User {
    private int userID;
    private String name;
    private String lastName;
    private String email;
    private String username;
    private String passw;
    private String role;


    public User(int userID, String name, String email, String username, String passw, String role, String lastName){
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.username = username;
        this.passw = passw;
        this.role = role;
        this.lastName = lastName;
    }
    public User() {

    }

    public User(String name, String email, String username, String passw, String role) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.passw = passw;
        this.role = role;
    }


    public int getUserID() {
        return userID;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
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

    public String getLastName() {
        return lastName;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

