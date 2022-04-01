package com.example.IDATT2015QS3REST.repository;

import com.example.IDATT2015QS3REST.model.Subject;
import com.example.IDATT2015QS3REST.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class jdbcSubjectRepository implements SubjectRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int addSubject(Subject subject) {
        System.out.println("Adding...");
        jdbcTemplate.update("INSERT INTO subject (subjectCode, subjectName, assignmentAmount, queueSize) VALUES(?,?,?,0)",
                new Object[] {subject.getSubjectCode(), subject.getSubjectName(), subject.getAssignmentAmount()});
        System.out.println("Added subject");

        System.out.println("Receiving ID...");
        Subject subject1 = jdbcTemplate.queryForObject("SELECT subjectId FROM subject WHERE subjectCode=? AND subjectName=?",
                BeanPropertyRowMapper.newInstance(Subject.class), subject.getSubjectCode(), subject.getSubjectName());

        int subjectId = subject1.getSubjectId();
        System.out.println("Found ID..." + subjectId);

        System.out.println("Adding into assignments...");
        for(int i=0; i < subject.getAssignmentAmount(); i++){
            jdbcTemplate.update("INSERT INTO assignment (assignmentNumber, subjectId) VALUES(?,?)",
                    new Object[] {i+1,subjectId});
        }
        System.out.println("Success");
        return subjectId;
    }

    @Override
    public List<Subject> getAllSubjects(int id){
        String sql = ("SELECT * FROM subjectUser JOIN subject ON(subjectUser.subjectId = subject.subjectId) WHERE subjectUser.userId=?");
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Subject.class), id);
    }

    @Override
    public int getQueueSize(int subjectId) {
        int size = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM subjectQueue WHERE subjectId=?",
                new Object[] {subjectId}, Integer.class);

        return size;
    }

    @Override
    public int setQueueSize(int subjectId, int queueSize) {
        System.out.println("Setter size fra repo med subjectId" + subjectId + " til " + queueSize);

        int response = jdbcTemplate.update("UPDATE subject SET queueSize = ? WHERE subjectId = ?",
                new Object[] {queueSize, subjectId});

        return response;
    }
}
