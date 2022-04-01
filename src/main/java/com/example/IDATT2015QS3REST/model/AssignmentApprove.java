package com.example.IDATT2015QS3REST.model;

import com.fasterxml.jackson.annotation.JsonCreator;


public class AssignmentApprove {
    private String name;
    private int subjectId;
    private int assignmentNumber;
    private int position;

    @JsonCreator
    public AssignmentApprove(String name, int subjectId, int assignmentNumber){
        this.name = name;
        this.subjectId = subjectId;
        this.assignmentNumber = assignmentNumber;
    }

    @JsonCreator
    public AssignmentApprove(){

    }

    public String getName() {
        return name;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public int getAssignmentNumber() {
        return assignmentNumber;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
