package com.example.IDATT2015QS3REST.service;


import com.example.IDATT2015QS3REST.model.SubjectQueue;
import com.example.IDATT2015QS3REST.model.SubjectQueueJoinObject;
import com.example.IDATT2015QS3REST.repository.SubjectQueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectQueueService {
    @Autowired
    SubjectQueueRepository subjectQueueRepository;

    public int addSubjectQueue(SubjectQueue subjectQueue) {
        return subjectQueueRepository.addSubjectQueue(subjectQueue);
    }

    public List<SubjectQueueJoinObject> getAllSubjectQueues(int subjectQueueId){
        List<SubjectQueueJoinObject> subjectQueues = new ArrayList<SubjectQueueJoinObject>();

        subjectQueueRepository.getAllSubjectQueues(subjectQueueId).forEach(subjectQueues::add);

        System.out.println(subjectQueues.size());

        return subjectQueues;
    }

    public List<SubjectQueue> getSubjectQueueUser(int subjectId, int userId){
        List<SubjectQueue> subjectQueueUser = new ArrayList<SubjectQueue>();

        subjectQueueRepository.getSubjectQueueUser(subjectId, userId).forEach(subjectQueueUser::add);

        return subjectQueueUser;
    }

    public boolean userInQueue(int userId) {

        int userInQueue = subjectQueueRepository.userInQueue(userId);

        return userInQueue > 0;
    }

    public int leaveQueue(SubjectQueue subjectQueue) {
        return subjectQueueRepository.leaveQueue(subjectQueue);
    }
}
