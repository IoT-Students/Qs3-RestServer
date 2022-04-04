package com.example.IDATT2015QS3REST.repository;

import com.example.IDATT2015QS3REST.model.Assignment;
import com.example.IDATT2015QS3REST.model.AssignmentApprove;
import com.example.IDATT2015QS3REST.model.SubjectQueue;

import java.util.List;

/**
 * An interface that represents AssignmentRepository
 */
public interface AssignmentRepository {
    /**
     * An overrideable method for approving a student
     * @param assignmentApprove an object that holds necessary information about approvement
     * @return returns an int
     */
    int approveAssignment(AssignmentApprove assignmentApprove);

    /**
     * A method that retrieves all assignments in a subject belonging to a specific user
     * @param userId The user that is retrieving the assignment
     * @param subjectId the subject the assignment belongs to
     * @return returns a list of subjects
     */
    List<Assignment> getAllAssignmentsSubject(int userId, int subjectId);


    /**
     * A method that deletes a user from queue
     * @param assignmentApprove
     * @return returns an int for status
     */
    int deleteFromQueue(AssignmentApprove assignmentApprove);


    /**
     * A method that dynamically updates the users position in the queue as users are being removed ar set to hold
     * @param assignmentApprove
     * @return returns a status int
     */
    int updatePosition(AssignmentApprove assignmentApprove);
}
