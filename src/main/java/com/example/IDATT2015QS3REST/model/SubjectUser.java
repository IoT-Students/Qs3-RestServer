package com.example.IDATT2015QS3REST.model;

public class SubjectUser {
    private int subjectId;
    private String userDetails;

    public SubjectUser(String userDetails, int subjectId){
        this.userDetails = userDetails;
        this.subjectId = subjectId;

    }
    public SubjectUser() {

    }

    public String getUserDetails() {
        return userDetails;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setUserDetails(String username) {
        this.userDetails = username;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }
}
