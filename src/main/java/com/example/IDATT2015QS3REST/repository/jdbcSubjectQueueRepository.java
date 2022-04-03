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

@Repository
public class jdbcSubjectQueueRepository implements SubjectQueueRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger LOGGER = LogManager.getLogger(LoginController.class);

    @Override
    public int addSubjectQueue(SubjectQueue subjectQueue) {

        int position = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM subjectQueue WHERE subjectId=?",
                new Object[] {subjectQueue.getSubjectId()}, Integer.class);

        System.out.println("Position is " + position);
        subjectQueue.setPosition(position + 1);

        return jdbcTemplate.update("INSERT INTO subjectQueue (campus, building, room, tabl, assignment, type, subjectId, userId, position, status) VALUES(?,?,?,?,?,?,?,?,?,?)",
                new Object[] {subjectQueue.getCampus(), subjectQueue.getBuilding(), subjectQueue.getRoom(), subjectQueue.getTabl(), subjectQueue.getAssignment(), subjectQueue.getType(), subjectQueue.getSubjectId(), subjectQueue.getUserId(), subjectQueue.getPosition(), false});
    }
    @Override
    public List<SubjectQueueJoinObject> getAllSubjectQueues(int subjectQueueId){
        LOGGER.info("Jeg sender nå en sql spørring for alle subjectQueues");
        String sql = ("SELECT subjectQueue.*, users.name FROM subjectQueue JOIN users ON(subjectQueue.userId = users.userId) WHERE subjectId=?");

        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(SubjectQueueJoinObject.class), subjectQueueId);

    }
    @Override
    public List<SubjectQueue> getSubjectQueueUser(int subjectQueueId, int userId){
        LOGGER.info("Jeg henter nå ut kø-objektet for en bruker");
        String sql = ("SELECT * FROM subjectQueue JOIN users ON(subjectQueue.userId = users.userId) WHERE subjectId=? AND subjectQueue.userId=?");
        LOGGER.info(subjectQueueId);
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(SubjectQueue.class), subjectQueueId, userId);

    }

    @Override
    public int userInQueue(int userId) {
        LOGGER.info("Sjekker om brukeren er i kø");
        String sql = ("SELECT COUNT(*) FROM subjectQueue WHERE userId = ?");
        return jdbcTemplate.queryForObject(sql, new Object[]{userId}, Integer.class);
    }

    @Override
    public int leaveQueue(SubjectQueue subjectQueue) {
        LOGGER.info("Fjerner bruker fra kø");
        return jdbcTemplate.update("DELETE FROM subjectQueue WHERE subjectQueueId = ? ",
                new Object[]{subjectQueue.getSubjectQueueId()});
    }

    @Override
    public int updateQueue(int userId, int subjectId) {
        LOGGER.info("Jeg oppdaterer status til en bruker");

        return  jdbcTemplate.update("UPDATE subjectQueue SET status = true WHERE subjectId=? AND userId=?",
                new Object[] { subjectId, userId});
    }
}
