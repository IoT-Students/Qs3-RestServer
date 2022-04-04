package com.example.IDATT2015QS3REST.repository;


import com.example.IDATT2015QS3REST.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * A class that represents a repository for communication with assignment database
 * @Repository signals it is a repo
 */
@Repository
public class JdbcAssigmentRepository implements AssignmentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * An overrideable method for approving a student
     * @param assignmentApprove an object that holds necessary information about approvement: userId, subjectId and assignmentId
     * @return returns a status int
     */
    @Override
    public int approveAssignment(AssignmentApprove assignmentApprove) {
        return jdbcTemplate.update("UPDATE userAssignment JOIN assignment ON(userAssignment.assignmentId = assignment.assignmentId) JOIN users ON(userAssignment.userId = users.userId) SET userAssignment.status = true WHERE users.userId=? AND assignment.subjectId=? AND assignment.assignmentNumber=?",
                new Object[]{assignmentApprove.getUserId(), assignmentApprove.getSubjectId(), assignmentApprove.getAssignmentNumber()});
    }

    /**
     * A method that deletes a user from queue
     * @param assignmentApprove The subjectQueueId that is being deleted
     * @return returns an int for status
     */

    @Override
    public int deleteFromQueue(AssignmentApprove assignmentApprove) {
        return jdbcTemplate.update("DELETE FROM subjectQueue WHERE subjectQueueId = ? ",
                new Object[]{assignmentApprove.getSubjectQueueId()});
    }

    /**
     * A method that dynamically updates the users position in the queue as users are being removed ar set on hold
     * @param assignmentApprove The current position of the user and subjectId for the subject that is being updated
     * @return returns a status int
     */

    @Override
    public int updatePosition(AssignmentApprove assignmentApprove) {
        return jdbcTemplate.update("UPDATE subjectQueue SET position = position - 1 WHERE subjectId = ? AND position > ?",
                new Object[] { assignmentApprove.getSubjectId(), assignmentApprove.getPosition()});
    }

    /**
     * A method that retrieves all assignments in a subject belonging to a specific user
     * @param userId The user that is retrieving the assignment
     * @param subjectId the subject the assignment belongs to
     * @return returns a list of subjects
     */
    @Override
    public List<Assignment> getAllAssignmentsSubject(int userId, int subjectId){
        String sql = ("SELECT assignment.assignmentNumber, assignment.assignmentId, userAssignment.status FROM assignment JOIN userAssignment ON(assignment.assignmentId = userAssignment.assignmentId) WHERE userAssignment.userId=? AND assignment.subjectId=?");
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Assignment.class), userId, subjectId);
    }
}
