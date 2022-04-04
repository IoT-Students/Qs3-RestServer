package com.example.IDATT2015QS3REST.repository;

import com.example.IDATT2015QS3REST.model.SubjectQueue;
import com.example.IDATT2015QS3REST.model.SubjectQueueJoinObject;

import java.util.List;

public interface SubjectQueueRepository {

    /**
     * A method for adding subjectQueue to database
     * First finds position for placing the object in the right place in queue
     * @param subjectQueue the subjectQueue object that is being added
     * @return returns a status int
     */
    int addSubjectQueue(SubjectQueue subjectQueue);

    /**
     * A method for retrieving all subjectQueues in a subject
     * @param subjectId the id for the subject that thr queue is retrieved from
     * @return returns a list of SubjectQueueObject objects
     */
    List<SubjectQueueJoinObject> getAllSubjectQueues(int subjectId);

    /**
     * A method for retrieving subjectQueue for a specific user and for a specific subject
     * @param subjectId the id for the subject that is the queue is retrieved from
     * @param userId the id for the user that is in a queue
     * @return returns a list with the user that is in a subjectQueue
     */
    List<SubjectQueue> getSubjectQueueUser(int subjectId, int userId);

    /**
     * A method for checking if a user is in a queue
     * @param userId the userId for the user that is being checked
     * @return returns a status int. 1 if exist, 0 if not
     */
    int userInQueue(int userId);

    /**
     * A method for updating the status if a student get help or not from a studass in a queue for a subject
     * @param userId the userId that is being updated
     * @param subjectId the subjectId that the queue is being updated
     * @return returns a status int
     */

    int updateQueue(int userId, int subjectId);

    /**
     * A method for checking if a user is in a queue independent of a subject
     * @param userId the userId that is being checked
     * @return returns a list of the user i queue
     */
    List<SubjectQueue> getUserInQueue(int userId);
}
