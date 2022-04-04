package com.example.IDATT2015QS3REST.model;

/**
 * A class for holding the subject user
 * This is for connecting a user with a subject
 */
public class SubjectUser {
    private int subjectId;
    private String userDetails;

    /**
     * The constructor for the subject user
     * @param userDetails A String that holds information about the user in this order
     *                    lastName, name, email
     * @param subjectId The subjectId
     */
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
