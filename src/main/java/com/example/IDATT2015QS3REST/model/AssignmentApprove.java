package com.example.IDATT2015QS3REST.model;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * A class for holding all information needed to approve an assignment
 */
public class AssignmentApprove {
    private int subjectQueueId;
    private int userId;
    private int subjectId;
    private int assignmentNumber;
    private int position;

    /**
     * The constructor for assignmentApprove
     * @param subjectQueueId The subjectQueueId
     * @param userId The userId
     * @param subjectId The subjectId
     * @param assignmentNumber The assignmentNumber
     */
    @JsonCreator
    public AssignmentApprove(int subjectQueueId, int userId, int subjectId, int assignmentNumber){
        this.subjectQueueId = subjectQueueId;
        this.userId = userId;
        this.subjectId = subjectId;
        this.assignmentNumber = assignmentNumber;
    }

    @JsonCreator
    public AssignmentApprove(){

    }

    public int getSubjectQueueId() {
        return subjectQueueId;
    }

    public int getUserId() {
        return userId;
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
