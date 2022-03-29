package com.example.IDATT2015QS3REST.repository;

import com.example.IDATT2015QS3REST.model.Subject;
import com.example.IDATT2015QS3REST.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class jdbcSubjectRepository implements SubjectRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int addSubject(Subject subject) {
        return jdbcTemplate.update("INSERT INTO subject (subjectCode, subjectName) VALUES(?,?)",
                new Object[] {subject.getSubjectCode(), subject.getSubjectName()});
    }


}