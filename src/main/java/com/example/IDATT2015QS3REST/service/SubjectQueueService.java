package com.example.IDATT2015QS3REST.service;


import com.example.IDATT2015QS3REST.controller.LoginController;
import com.example.IDATT2015QS3REST.model.SubjectQueue;
import com.example.IDATT2015QS3REST.model.SubjectQueueJoinObject;
import com.example.IDATT2015QS3REST.repository.SubjectQueueRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * A service class for subjectQueue.
 */
@Service
public class SubjectQueueService {
    @Autowired
    SubjectQueueRepository subjectQueueRepository;

    private static final Logger LOGGER = LogManager.getLogger(SubjectQueueService.class);

    /**
     * A method for adding a queue object in a subject
     * @param subjectQueue Holds the data needed to add a user to a queue in a subject
     * @return A status as an integer
     */
    public int addSubjectQueue(SubjectQueue subjectQueue) {
        LOGGER.info("Service: Adding student to queue");
        return subjectQueueRepository.addSubjectQueue(subjectQueue);
    }

    /**
     * A method for fetching a subjectQueueJoinObject to make it visible for
     * the student assistance who the student is and where he/she is
     * @param subjectQueueId The subjectQueueId
     * @return a List of one subjectQueueJoinObject
     */
    public List<SubjectQueueJoinObject> getAllSubjectQueues(int subjectQueueId){
        LOGGER.info("Service: Fetching the queue");
        List<SubjectQueueJoinObject> subjectQueues = new ArrayList<SubjectQueueJoinObject>();

        subjectQueueRepository.getAllSubjectQueues(subjectQueueId).forEach(subjectQueues::add);

        System.out.println(subjectQueues.size());

        return subjectQueues;
    }

    /**
     * A method for fetching subjectQueueUser from a subjectqueue
     * @param subjectId
     * @param userId
     * @return subjectQueueUser
     */
    public List<SubjectQueue> getSubjectQueueUser(int subjectId, int userId){
        LOGGER.info("Service: Fetching student in queue");
        List<SubjectQueue> subjectQueueUser = new ArrayList<SubjectQueue>();

        subjectQueueRepository.getSubjectQueueUser(subjectId, userId).forEach(subjectQueueUser::add);

        return subjectQueueUser;
    }

    /**
     * A method for checking if a user is already in queue
     * @param userId
     * @return a boolean
     */
    public boolean userInQueue(int userId) {
        LOGGER.info("Service: Checking if a student is in a queue");
        int userInQueue = subjectQueueRepository.userInQueue(userId);

        return userInQueue > 0;
    }

    /**
     * A method for updating when a student is assigned a student assistance
     * A status will be set to true or false
     * @param userId The userId
     * @param subjectId The subjectId
     * @return A status as an integer
     */
    public int updateQueue(int userId, int subjectId){
        LOGGER.info("Service: Updating status of a queue object");
        return subjectQueueRepository.updateQueue(userId, subjectId);
    }

    /**
     * A method for fetching a student from a queue, when subjectId is unknown.
     * @param userId The userId
     * @return A list with a subjectQueue
     */
    public List<SubjectQueue> getUserInQueue(int userId) {
        LOGGER.info("Service: Fetching a student in queue without subject id");
        return subjectQueueRepository.getUserInQueue(userId);
    }
}
