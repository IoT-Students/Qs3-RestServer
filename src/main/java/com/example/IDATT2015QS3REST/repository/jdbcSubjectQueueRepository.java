package com.example.IDATT2015QS3REST.repository;

import com.example.IDATT2015QS3REST.controller.LoginController;
import com.example.IDATT2015QS3REST.model.SubjectQueue;
import com.example.IDATT2015QS3REST.model.SubjectQueueJoinObject;
import com.example.IDATT2015QS3REST.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * A class that represents a repository for handling queue objects
 */

@Repository
public class jdbcSubjectQueueRepository implements SubjectQueueRepository {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger LOGGER = LogManager.getLogger(LoginController.class);

    /**
     * A method for adding subjectQueue to database
     * Finds position first for placing the object in the right place in queue
     * @param subjectQueue the subjectQueue object that is being added
     * @return returns a status int
     */
    @Override
    public int addSubjectQueue(SubjectQueue subjectQueue) {

        int position = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM subjectQueue WHERE subjectId=?",
                new Object[] {subjectQueue.getSubjectId()}, Integer.class);

        System.out.println("Position is " + position);
        subjectQueue.setPosition(position + 1);

        return jdbcTemplate.update("INSERT INTO subjectQueue (campus, building, room, tabl, assignment, type, subjectId, userId, position, status) VALUES(?,?,?,?,?,?,?,?,?,?)",
                new Object[] {subjectQueue.getCampus(), subjectQueue.getBuilding(), subjectQueue.getRoom(), subjectQueue.getTabl(), subjectQueue.getAssignment(), subjectQueue.getType(), subjectQueue.getSubjectId(), subjectQueue.getUserId(), subjectQueue.getPosition(), false});
    }

    /**
     * A method for retrieving all subjectQueues in a subject
     * @param subjectId the id for the subject that thr queue is retrieved from
     * @return returns a list of SubjectQueueObject objects
     */
    @Override
    public List<SubjectQueueJoinObject> getAllSubjectQueues(int subjectId){
        LOGGER.info("Jeg sender nå en sql spørring for alle subjectQueues");
        String sql = ("SELECT subjectQueue.*, users.name FROM subjectQueue JOIN users ON(subjectQueue.userId = users.userId) WHERE subjectId=?");

        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(SubjectQueueJoinObject.class), subjectId);

    }

    /**
     * A method for retrieving subjectQueue for a specific user and for a specific subject
     * @param subjectId the id for the subject that is the queue is retrieved from
     * @param userId the id for the user that is in a queue
     * @return returns a list with the user that is in a subjectQueue
     */
    @Override
    public List<SubjectQueue> getSubjectQueueUser(int subjectId, int userId){
        LOGGER.info("Jeg henter nå ut kø-objektet for en bruker");
        String sql = ("SELECT * FROM subjectQueue JOIN users ON(subjectQueue.userId = users.userId) WHERE subjectId=? AND subjectQueue.userId=?");
        LOGGER.info(subjectId);
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(SubjectQueue.class), subjectId, userId);

    }

    /**
     * A method for checking if a user is in a queue
     * @param userId the userId for the user that is being checked
     * @return returns a status int. 1 if exist, 0 if not
     */

    @Override
    public int userInQueue(int userId) {
        LOGGER.info("Sjekker om brukeren er i kø");
        String sql = ("SELECT COUNT(*) FROM subjectQueue WHERE userId = ?");
        return jdbcTemplate.queryForObject(sql, new Object[]{userId}, Integer.class);
    }

    /**
     * A method for updating the status if a student get help or not from a studass in a queue for a subject
     * @param userId the userId that is being updated
     * @param subjectId the subjectId that the queue is being updated
     * @return returns a status int
     */

    @Override
    public int updateQueue(int userId, int subjectId) {
        LOGGER.info("Jeg oppdaterer status til en bruker");

        int status = jdbcTemplate.queryForObject("SELECT status FROM subjectQueue WHERE subjectId=? AND userId=?",
                new Object[] { subjectId, userId},Integer.class);

       if(status == 0){
           return  jdbcTemplate.update("UPDATE subjectQueue SET status = true WHERE subjectId=? AND userId=?",
                   new Object[] { subjectId, userId});

       }else{
           return  jdbcTemplate.update("UPDATE subjectQueue SET status = false WHERE subjectId=? AND userId=?",
                   new Object[] { subjectId, userId});
       }
    }

    /**
     * A method for checking if a user is in a queue independent of a subject
     * @param userId the userId that is being checked
     * @return returns a list of the user i queue
     */

    @Override
    public List<SubjectQueue> getUserInQueue(int userId) {
        return jdbcTemplate.query("SELECT * FROM subjectQueue WHERE userId = ?", BeanPropertyRowMapper.newInstance(SubjectQueue.class), userId);
    }
}
