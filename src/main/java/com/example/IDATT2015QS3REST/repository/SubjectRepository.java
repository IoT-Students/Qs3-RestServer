package com.example.IDATT2015QS3REST.repository;

import com.example.IDATT2015QS3REST.model.Subject;

import java.util.List;

/**
 * An interface that represents a SubjectRepository with overridable methods for handling events according subjects
 */

public interface SubjectRepository {

    /**
     * A method for adding a subject
     * @param subject the subject that is being added
     * @return returns a status
     */
    int addSubject(Subject subject);

    /**
     * A method for retrieving all subjects for a user
     * @param userId the userId the subjects are belonging to
     * @return returns a list of subjects
     */

    List<Subject> getAllSubjects(int userId);

    /**
     * A method for finding the queue size for a subject
     * @param subjectId the subject we are checking the queue size for
     * @return returns a status as an int
     */

    int getQueueSize(int subjectId);

    /**
     * A method for setting queue size
     * @param subjectId the subjectId that the queue size is updated for
     * @param queueSize the queue size that is being set
     * @return returns a status as an int
     */
    int setQueueSize(int subjectId, int queueSize);
}
