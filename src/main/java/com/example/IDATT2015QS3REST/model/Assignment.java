package com.example.IDATT2015QS3REST.model;

public class Assignment {
    private int assignmentId;
    private int subjectId;
    private int assignmentNumber;

    public Assignment(int assignmentId, int subjectId, int assignmentNumber){
        this.assignmentId = assignmentId;
        this.subjectId = subjectId;
        this.assignmentNumber = assignmentNumber;
    }
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
}
