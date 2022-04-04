package com.example.IDATT2015QS3REST.model;

/**
 * This is a class for holding an assignment
 */

public class Assignment {
    private int assignmentId;
    private int subjectId;
    private int assignmentNumber;
    private boolean status;

    /**
     * Constructor for the assignment
     * @param assignmentId
     * @param subjectId
     * @param assignmentNumber
     * @param status
     */

    public Assignment(int assignmentId, int subjectId, int assignmentNumber, boolean status){
        this.assignmentId = assignmentId;
        this.subjectId = subjectId;
        this.assignmentNumber = assignmentNumber;
        this.status = status;
    }

    /**
     * Empty constructor. Makes it possible to create an assignment after a call to the SQL database
     */
    public Assignment(){

    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public int getAssignmentNumber() {
        return assignmentNumber;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public void setAssignmentNumber(int assignmentNumber) {
        this.assignmentNumber = assignmentNumber;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
