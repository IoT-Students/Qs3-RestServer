package com.example.IDATT2015QS3REST.model;

public class SubjectUser {
    private int subjectId;
    private String username;

    public SubjectUser(String username, int subjectId){
        this.username = username;
        this.subjectId = subjectId;

    }
    public SubjectUser() {

    }

    public String getUsername() {
        return username;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }
}
