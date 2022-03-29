package com.example.IDATT2015QS3REST.repository;

import com.example.IDATT2015QS3REST.model.Subject;
import com.example.IDATT2015QS3REST.model.SubjectUser;
import com.example.IDATT2015QS3REST.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class JdbcSubjectStudentRepository implements SubjectStudentRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int addStudents(SubjectUser subjectUser) {

        User user1 = jdbcTemplate.queryForObject("SELECT userID FROM users WHERE username=?",
                BeanPropertyRowMapper.newInstance(User.class),subjectUser.getUsername());

        int userID = user1.getUserID();
        int subjectID = subjectUser.getSubjectId();

        return jdbcTemplate.update("INSERT INTO subjectuser (userId, subjectId) VALUES(?,?)",
                new Object[] {userID, subjectID});

    }

}
