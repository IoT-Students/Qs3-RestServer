package com.example.IDATT2015QS3REST.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Subject {
    private int subjectId;
    private String subjectCode;
    private String subjectName;

    @JsonCreator
    public Subject(@JsonProperty("subjectId") final int subjectId , @JsonProperty("subjectCode") final String subjectCode, @JsonProperty("subjectName") final String subjectName) {
        this.subjectId = subjectId;
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
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

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
