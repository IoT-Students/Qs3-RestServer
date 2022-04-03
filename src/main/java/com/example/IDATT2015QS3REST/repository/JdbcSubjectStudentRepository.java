package com.example.IDATT2015QS3REST.repository;

import com.example.IDATT2015QS3REST.model.Assignment;
import com.example.IDATT2015QS3REST.model.Subject;
import com.example.IDATT2015QS3REST.model.SubjectUser;
import com.example.IDATT2015QS3REST.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class JdbcSubjectStudentRepository implements SubjectStudentRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int addStudent(SubjectUser subjectUser) {

        User user1 = jdbcTemplate.queryForObject("SELECT userID FROM users WHERE name=?",
                BeanPropertyRowMapper.newInstance(User.class),subjectUser.getName());

        int userID = user1.getUserID();
        int subjectID = subjectUser.getSubjectId();

        Subject subject = jdbcTemplate.queryForObject("SELECT assignmentAmount FROM subject WHERE subjectId=?",
                BeanPropertyRowMapper.newInstance(Subject.class),subjectID);

        int assignmentAmount = subject.getAssignmentAmount();


        for(int i=0; i < assignmentAmount; i++){
            Assignment assignment = jdbcTemplate.queryForObject("SELECT assignmentId FROM assignment WHERE subjectId=? AND assignmentNumber=?",
                    BeanPropertyRowMapper.newInstance(Assignment.class),subjectID, i+1);

            int assignmentId = assignment.getAssignmentId();

            jdbcTemplate.update("INSERT INTO userAssignment (userId, assignmentId,status) VALUES(?,?,?)",
                    new Object[] {userID, assignmentId, false});
        }

        return jdbcTemplate.update("INSERT INTO subjectUser (userId, subjectId) VALUES(?,?)",
                new Object[] {userID, subjectID});

    }

    @Override
    public int addTeacher(SubjectUser subjectUser) {

        User user1 = jdbcTemplate.queryForObject("SELECT userID FROM users WHERE name=?",
                BeanPropertyRowMapper.newInstance(User.class),subjectUser.getName());

        int userID = user1.getUserID();
        int subjectID = subjectUser.getSubjectId();


        return jdbcTemplate.update("INSERT INTO subjectUser (userId, subjectId) VALUES(?,?)",
                new Object[] {userID, subjectID});

    }

    @Override
    public List<User> getUsersSubject(int subjectId){
        String sql = ("SELECT users.userId, users.name, users.email, users.role FROM users JOIN subjectUser ON(subjectUser.userId = users.userId) WHERE subjectUser.subjectId=? AND (users.role ='Student' OR users.role = 'Studass')");
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(User.class), subjectId);

    }

}
