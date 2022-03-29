package com.example.IDATT2015QS3REST.repository;

import com.example.IDATT2015QS3REST.model.SubjectQueue;

import java.util.List;

public interface SubjectQueueRepository {
    int addSubjectQueue(SubjectQueue subjectQueue);

    List<SubjectQueue> getAllSubjectQueues(int subjectQueueId);
}
