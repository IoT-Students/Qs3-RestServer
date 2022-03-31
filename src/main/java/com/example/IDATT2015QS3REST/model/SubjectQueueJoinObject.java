package com.example.IDATT2015QS3REST.model;

public class SubjectQueueJoinObject {
    private int subjectQueueId;
    private String name;
    private String campus;
    private String building;
    private String room;
    private String tabl;
    private String assignments;
    private int type;
    private int userId;
    private int subjectId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getTabl() {
        return tabl;
    }

    public void setTabl(String tabl) {
        this.tabl = tabl;
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

    public int getSubjectQueueId() {
        return subjectQueueId;
    }

    public void setSubjectQueueId(int subjectQueueId) {
        this.subjectQueueId = subjectQueueId;
    }
}
