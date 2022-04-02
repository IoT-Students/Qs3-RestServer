package com.example.IDATT2015QS3REST.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Subject {
    private int subjectId;
    private String subjectCode;
    private String subjectName;
    private int assignmentAmount;
    private int requiredAssignments;
    private int queueSize;

    @JsonCreator
    public Subject(@JsonProperty("subjectCode") String subjectCode, @JsonProperty("subjectName") String subjectName, @JsonProperty("assignmentAmount") int assignmentAmount,  @JsonProperty("requiredAssignments") int requiredAssignments) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.assignmentAmount = assignmentAmount;
        this.requiredAssignments = requiredAssignments;
    }

    public Subject(){

    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public int getAssignmentAmount() {
        return assignmentAmount;
    }

    public int getRequiredAssignments() {
        return requiredAssignments;
    }

    public void setRequiredAssignments(int requiredAssignments) {
        this.requiredAssignments = requiredAssignments;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setAssignmentAmount(int assignmentAmount) {
        this.assignmentAmount = assignmentAmount;
    }

    public int getQueueSize() {
        return queueSize;
    }

    public void setQueueSize(int queueSize) {
        this.queueSize = queueSize;
    }
}
