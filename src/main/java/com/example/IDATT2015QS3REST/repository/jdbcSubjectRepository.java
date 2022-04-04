package com.example.IDATT2015QS3REST.repository;


import com.example.IDATT2015QS3REST.model.Subject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * A class that represents a repository for handling subjecg events
 */

@Repository
public class jdbcSubjectRepository implements SubjectRepository {

    private static final Logger LOGGER = LogManager.getLogger(jdbcSubjectRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * A method for adding a subject and update assignment table
     *
     * @param subject the subject you want to add
     * @return returns a status int
     */
    @Override
    public int addSubject(Subject subject) {
        LOGGER.info("Adding subject..");
        jdbcTemplate.update("INSERT INTO subject (subjectCode, subjectName, assignmentAmount,requiredAssignments, queueSize) VALUES(?,?,?,?,0)",
                new Object[] {subject.getSubjectCode(), subject.getSubjectName(), subject.getAssignmentAmount(),subject.getRequiredAssignments()});


        LOGGER.info("Receiving subjectId...");
        Subject subject1 = jdbcTemplate.queryForObject("SELECT subjectId FROM subject WHERE subjectCode=? AND subjectName=?",
                BeanPropertyRowMapper.newInstance(Subject.class), subject.getSubjectCode(), subject.getSubjectName());

        int subjectId = subject1.getSubjectId();


        LOGGER.info("Adding into assignments...");
        for(int i=0; i < subject.getAssignmentAmount(); i++){
            jdbcTemplate.update("INSERT INTO assignment (assignmentNumber, subjectId) VALUES(?,?)",
                    new Object[] {i+1,subjectId});
        }

        return subjectId;
    }

    /**
     * A method for retrieving all subjects for a user
     * @param userId the userId the subjects are belonging to
     * @return returns a list of subjects
     */

    @Override
    public List<Subject> getAllSubjects(int userId){
        LOGGER.info("Fetching all subjects for the user with ID: " + userId);
        String sql = ("SELECT * FROM subjectUser JOIN subject ON(subjectUser.subjectId = subject.subjectId) WHERE subjectUser.userId=?");
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Subject.class), userId);
    }

    /**
     * A method for finding the queue size for a subject
     * @param subjectId the subject we are checking the queue size for
     * @return returns a status as an int
     */

    @Override
    public int getQueueSize(int subjectId) {

        int size = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM subjectQueue WHERE subjectId=?",
                new Object[] {subjectId}, Integer.class);
        LOGGER.info("Finding queuesize: " + size);
        return size;
    }

    /**
     * A method for setting queue size
     * @param subjectId the subjectId that the queue size is updated for
     * @param queueSize the queue size that is being set
     * @return returns a status as an int
     */

    @Override
    public int setQueueSize(int subjectId, int queueSize) {
        LOGGER.info("Setting queuesize in subject with id: " + subjectId + " tp " + queueSize);

        int response = jdbcTemplate.update("UPDATE subject SET queueSize = ? WHERE subjectId = ?",
                new Object[] {queueSize, subjectId});

        return response;
    }
}
