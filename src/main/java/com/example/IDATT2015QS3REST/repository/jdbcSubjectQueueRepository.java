package com.example.IDATT2015QS3REST.repository;

import com.example.IDATT2015QS3REST.controller.LoginController;
import com.example.IDATT2015QS3REST.model.Subject;
import com.example.IDATT2015QS3REST.model.SubjectQueue;
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
        return jdbcTemplate.update("INSERT INTO subjectQueue (campus, building, room, tabl, assignments, type) VALUES(?,?,?,?,?,?)",
                new Object[] {subjectQueue.getCampus(), subjectQueue.getBuilding(), subjectQueue.getRoom(), subjectQueue.getTable(), subjectQueue.getAssignments(), subjectQueue.getType()});
    }

    @Override
    public List<SubjectQueue> getAllSubjectQueues(int subjectQueueId){
        LOGGER.info("Jeg sender nå en sql spørring for alle subjectQueues");
        String sql = ("SELECT * FROM subjectQueue JOIN users ON(subjectQueue.userId = users.userId) WHERE subjectId=?");

        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(SubjectQueue.class), subjectQueueId);

    }

    @Override
    public List<SubjectQueue> getSubjectQueueUser(int subjectQueueId, int userId){
        LOGGER.info("Jeg sender nå en sql spørring for kø for en user");
        String sql = ("SELECT * FROM subjectQueue JOIN users ON(subjectQueue.userId = users.userId) WHERE subjectId=? AND subjectQueue.userId=?");
        LOGGER.info(subjectQueueId);
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(SubjectQueue.class), subjectQueueId, userId);

    }





}