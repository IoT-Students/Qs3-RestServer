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

/**
 * A class that represents a repository for communication with subjectUser database
 */
@Repository
public class JdbcSubjectStudentRepository implements SubjectStudentRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * A method for adding a student to a subject.
     * @param lastName The lastname of the student who is added to the subject
     * @param name The firstname of the student who is added to the subject
     * @param subjectId The subjectId
     * @return status int
     */
    @Override
    public int addStudent(String lastName, String name, int subjectId) {

        //Finding the userId to the student
        User user1 = jdbcTemplate.queryForObject("SELECT userID FROM users WHERE name=? AND lastName=?",
                BeanPropertyRowMapper.newInstance(User.class), name, lastName);

        int userID = user1.getUserID();

        //Finding the total assignment amount for a subject
        Subject subject = jdbcTemplate.queryForObject("SELECT assignmentAmount FROM subject WHERE subjectId=?",
                BeanPropertyRowMapper.newInstance(Subject.class),subjectId);

        int assignmentAmount = subject.getAssignmentAmount();

        //Connecting all the assignments in a subject to the student
        for(int i=0; i < assignmentAmount; i++){
            Assignment assignment = jdbcTemplate.queryForObject("SELECT assignmentId FROM assignment WHERE subjectId=? AND assignmentNumber=?",
                    BeanPropertyRowMapper.newInstance(Assignment.class),subjectId, i+1);

            int assignmentId = assignment.getAssignmentId();

            jdbcTemplate.update("INSERT INTO userAssignment (userId, assignmentId,status) VALUES(?,?,?)",
                    new Object[] {userID, assignmentId, false});
        }

        //Adding the student to the subject
        return jdbcTemplate.update("INSERT INTO subjectUser (userId, subjectId) VALUES(?,?)",
                new Object[] {userID, subjectId});

    }

    /**
     * A method for creating a student
     * @param lastName The lastname of the student who is created
     * @param name The firstname of the student who is created
     * @param email The email of the student who is created
     * @return status int
     */
    @Override
    public int createStudent(String lastName, String name, String email) {
        return jdbcTemplate.update("INSERT INTO users (name, lastName, email, role) VALUES(?,?,?, DEFAULT)",
                new Object[]{name, lastName, email});
    }

    /**
     * A method for checking if the user already exist in the database
     * @param lastName The lastname of the student
     * @param name The firstname the student
     * @return status int
     */
    @Override
    public boolean findUser(String lastName, String name) {
        try {
            User user = jdbcTemplate.queryForObject("SELECT * FROM users WHERE name=? AND lastName=?",
                    BeanPropertyRowMapper.newInstance(User.class), name, lastName);
            return user != null;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }

    /**
     * A method for finding a user in a subject
     * @param lastName The lastname of the student
     * @param name The fistname of the student
     * @param subjectid The subjectId of the subject
     * @return
     */
    @Override
    public boolean findUserInSubject(String lastName, String name, int subjectid) {
        try {
            int userId = jdbcTemplate.queryForObject("SELECT userID FROM users WHERE name=? AND lastName=?",
                    new Object[] {name, lastName}, Integer.class);

            User user = jdbcTemplate.queryForObject("SELECT * FROM subjectUser WHERE userId=? AND subjectId=?",
                    BeanPropertyRowMapper.newInstance(User.class), userId, subjectid);
            return user != null;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }

    /**
     * Method for adding teacher or student assistance to a subject
     * @param subjectUser The subjectUser object that hold userDetails and subjectId
     * @return status int
     */
    @Override
    public int addTeacher(SubjectUser subjectUser) {

        String[] userDetails = subjectUser.getUserDetails().split(",");
        System.out.println(userDetails[0] + userDetails[1] + userDetails[2]);
        User user1 = jdbcTemplate.queryForObject("SELECT userID FROM users WHERE name=? AND lastName=?",
                BeanPropertyRowMapper.newInstance(User.class), userDetails[1], userDetails[0]);

        int userID = user1.getUserID();
        int subjectID = subjectUser.getSubjectId();


        return jdbcTemplate.update("INSERT INTO subjectUser (userId, subjectId) VALUES(?,?)",
                new Object[] {userID, subjectID});

    }

    /**
     * A method for fetching all users in a subject
     * @param subjectId The subjectId for the subject
     * @return a list of users
     */
    @Override
    public List<User> getUsersSubject(int subjectId){
        String sql = ("SELECT users.userId, users.name, users.email, users.role FROM users JOIN subjectUser ON(subjectUser.userId = users.userId) WHERE subjectUser.subjectId=? AND (users.role ='Student' OR users.role = 'Studass')");
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(User.class), subjectId);

    }

}
