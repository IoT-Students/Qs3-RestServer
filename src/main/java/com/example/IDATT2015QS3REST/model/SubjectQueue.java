package com.example.IDATT2015QS3REST.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SubjectQueue {
    private String campus;
    private String building;
    private String room;
    private String table;
    private String assignments;
    private int type;
    private int userId;
    private int subjectId;

    @JsonCreator
    public SubjectQueue(@JsonProperty("campus") final String campus , @JsonProperty("building") final String building, @JsonProperty("room") final String room, @JsonProperty("table") final String table, @JsonProperty("assignments") final String assignments, @JsonProperty("type") final int type, @JsonProperty("userId") final int userId, @JsonProperty("subjectId") final int subjectId){
        this.campus = campus;
        this.building = building;
        this.room = room;
        this.table = table;
        this.assignments = assignments;
        this.type = type;
        this.userId = userId;
        this.subjectId = subjectId;
    }

    public SubjectQueue(){

    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getAssignments() {
        return assignments;
    }

    public void setAssignments(String assignments) {
        this.assignments = assignments;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }
}
