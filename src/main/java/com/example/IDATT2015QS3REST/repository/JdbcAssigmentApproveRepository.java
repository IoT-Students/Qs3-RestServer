package com.example.IDATT2015QS3REST.repository;

import com.example.IDATT2015QS3REST.model.Assignment;
import com.example.IDATT2015QS3REST.model.AssignmentApprove;
import com.example.IDATT2015QS3REST.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcAssigmentApproveRepository implements AssignmentApproveRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int doAssignmentApprove(AssignmentApprove assignmentApprove) {

        return jdbcTemplate.update("UPDATE userAssignment JOIN assignment ON(userAssignment.assignmentId = assignment.assignmentId) JOIN users ON(userAssignment.userId = users.userId) SET userAssignment.status = true WHERE users.name=? AND assignment.subjectId=? AND assignment.assignmentNumber=?",
                new Object[] { assignmentApprove.getName(), assignmentApprove.getSubjectId(), assignmentApprove.getAssignmentNumber()});

    }
}
