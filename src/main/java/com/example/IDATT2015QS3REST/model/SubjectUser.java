package com.example.IDATT2015QS3REST.model;

public class SubjectUser {
    private int subjectId;
    private String name;

    public SubjectUser(String name, int subjectId){
        this.name = name;
        this.subjectId = subjectId;

    }
    public SubjectUser() {

    }

    public String getName() {
        return name;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setName(String username) {
        this.name = username;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }
}
