package com.example.IDATT2015QS3REST.repository;


import com.example.IDATT2015QS3REST.model.Assignment;
import com.example.IDATT2015QS3REST.model.AssignmentApprove;
import com.example.IDATT2015QS3REST.model.Subject;
import com.example.IDATT2015QS3REST.model.User;
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
        int update = jdbcTemplate.update("UPDATE userAssignment JOIN assignment ON(userAssignment.assignmentId = assignment.assignmentId) JOIN users ON(userAssignment.userId = users.userId) SET userAssignment.status = true WHERE users.name=? AND assignment.subjectId=? AND assignment.assignmentNumber=?",
                new Object[] { assignmentApprove.getName(), assignmentApprove.getSubjectId(), assignmentApprove.getAssignmentNumber()});

        User user1 = jdbcTemplate.queryForObject("SELECT userID FROM users WHERE name=?",
                BeanPropertyRowMapper.newInstance(User.class),assignmentApprove.getName());

        int userId = user1.getUserID();

        jdbcTemplate.update("DELETE FROM subjectQueue WHERE userId=? AND subjectId=?",
        new Object[] { userId, assignmentApprove.getSubjectId()});

        return update;

    }

    @Override
    public List<Assignment> getAllAssignmentsSubject(int userId, int subjectId){
        String sql = ("SELECT assignment.assignmentNumber, assignment.assignmentId, userAssignment.status FROM assignment JOIN userAssignment ON(assignment.assignmentId = userAssignment.assignmentId) WHERE userAssignment.userId=? AND assignment.subjectId=?");
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Assignment.class), userId, subjectId);

    }
}
