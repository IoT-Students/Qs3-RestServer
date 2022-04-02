package com.example.IDATT2015QS3REST.repository;

import com.example.IDATT2015QS3REST.model.SubjectQueue;
import com.example.IDATT2015QS3REST.model.SubjectQueueJoinObject;

import java.util.List;

public interface SubjectQueueRepository {
    int addSubjectQueue(SubjectQueue subjectQueue);

    List<SubjectQueueJoinObject> getAllSubjectQueues(int subjectQueueId);

    //Metode for å hente ut køen for en bruker
    List<SubjectQueue> getSubjectQueueUser(int subjectQueueId, int userId);

    int userInQueue(int userId);

    int leaveQueue(SubjectQueue subjectQueue);

    List<SubjectQueue> getUserInQueue(int userId);
}
