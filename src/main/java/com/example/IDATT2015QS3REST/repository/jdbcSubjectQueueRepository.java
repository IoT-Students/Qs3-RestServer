package com.example.IDATT2015QS3REST.repository;

import com.example.IDATT2015QS3REST.model.Subject;
import com.example.IDATT2015QS3REST.model.SubjectQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class jdbcSubjectQueueRepository implements SubjectQueueRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int addSubjectQueue(SubjectQueue subjectQueue) {
        return jdbcTemplate.update("INSERT INTO subjectQueue (campus, building, room, table, assignments, type) VALUES(?,?,?,?,?,?)",
                new Object[] {subjectQueue.getCampus(), subjectQueue.getBuilding(), subjectQueue.getRoom(), subjectQueue.getTable(), subjectQueue.getAssignments(), subjectQueue.getType()});
    }


}
