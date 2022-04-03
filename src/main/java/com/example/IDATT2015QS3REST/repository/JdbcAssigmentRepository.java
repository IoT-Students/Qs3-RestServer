package com.example.IDATT2015QS3REST.repository;


import com.example.IDATT2015QS3REST.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcAssigmentRepository implements AssignmentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int doAssignmentApprove(AssignmentApprove assignmentApprove) {
        int update = jdbcTemplate.update("UPDATE userAssignment JOIN assignment ON(userAssignment.assignmentId = assignment.assignmentId) JOIN users ON(userAssignment.userId = users.userId) SET userAssignment.status = true WHERE users.userId=? AND assignment.subjectId=? AND assignment.assignmentNumber=?",
                new Object[] { assignmentApprove.getUserId(), assignmentApprove.getSubjectId(), assignmentApprove.getAssignmentNumber()});


//        jdbcTemplate.update("DELETE FROM subjectQueue WHERE userId=? AND subjectId=?",
//        new Object[] { assignmentApprove.getUserId(), assignmentApprove.getSubjectId()});
//
//        jdbcTemplate.update("UPDATE subjectQueue SET position = position - 1 WHERE subjectId = ? AND position > ?",
//                new Object[] { assignmentApprove.getSubjectId(), assignmentApprove.getPosition()});

        return update;
    }

    @Override
    public int deleteFromQueue(AssignmentApprove assignmentApprove) {
        System.out.println("Fjerner bruker fra kø");
        return jdbcTemplate.update("DELETE FROM subjectQueue WHERE subjectQueueId = ? ",
                new Object[]{assignmentApprove.getSubjectQueueId()});
    }

    @Override
    public int updatePosition(AssignmentApprove assignmentApprove) {
        System.out.println("Oppdaterer posisjon til " + assignmentApprove.getUserId());
        return jdbcTemplate.update("UPDATE subjectQueue SET position = position - 1 WHERE subjectId = ? AND position > ?",
                new Object[] { assignmentApprove.getSubjectId(), assignmentApprove.getPosition()});
    }

    @Override
    public List<Assignment> getAllAssignmentsSubject(int userId, int subjectId){
        String sql = ("SELECT assignment.assignmentNumber, assignment.assignmentId, userAssignment.status FROM assignment JOIN userAssignment ON(assignment.assignmentId = userAssignment.assignmentId) WHERE userAssignment.userId=? AND assignment.subjectId=?");
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Assignment.class), userId, subjectId);
    }
}
