package com.example.IDATT2015QS3REST.controller;

import com.example.IDATT2015QS3REST.model.SubjectQueue;
import com.example.IDATT2015QS3REST.service.SubjectQueueService;
import com.example.IDATT2015QS3REST.service.SubjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is a controller class for the queue for all the subjects.
 * It is used for adding students to the queue, fetching all the students from a queue
 * ,fetching a single student from the queue, checking if a student is in a queue
 * and checking if a student is assigned a student assistance for help or not.
 */
@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/subjectQueue")
@CrossOrigin
public class SubjectQueueController {

    @Autowired
    private SubjectQueueService subjectQueueService;

    Logger logger = LoggerFactory.getLogger(SubjectQueueController.class);

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)

    /**
     * This is an endpoint for adding a student to a queue. There is a specific queue for each subject,
     * so in order to add a student to the correct queue it needs the subjectId
     * It also adds all needed data of the user, ex. name, location and the position in the queue.
     */
    public int addSubjectQueue(@RequestBody SubjectQueue subjectQueue) {
        logger.info("Adding the student: " + subjectQueue.getUserId() + " to the queue for the subject " + subjectQueue.getSubjectId());
        return subjectQueueService.addSubjectQueue(subjectQueue);
    }

    /**
     * This is an endpoint for fetching a queue for a subject
     * @param subjectId The subjectId
     * @return
     */
    @GetMapping("/{subjectId}")
    public List getAllSubjectQueues(@PathVariable("subjectId") int subjectId){
        logger.info("Fetching the queue from the subject: " + subjectId);
        return subjectQueueService.getAllSubjectQueues(subjectId);
    }

    /**
     * This is an endpoint for fetching a student from a queue
     * @param subjectId The subjectId for the subject queue
     * @param userId The userId
     * @return
     */
    @GetMapping("/{subjectId}/{userId}")
    public List getSubjectQueueUser(@PathVariable("subjectId") int subjectId, @PathVariable("userId") int userId){
        logger.info("Fetching all the subjectQueueUser from the subject with id: " + subjectId + " and userId " + userId);
        return subjectQueueService.getSubjectQueueUser(subjectId, userId);
    }

    /**
     * This is an endpoint for fetching a student from a queue, when subjectId is unknown.
     * @param userId
     * @return a subjectQueue object
     */
    @GetMapping("/queue/{userId}")
    public List getUserInQueue(@PathVariable("userId") int userId) {
        logger.info("Fetching student from the queue with userId: " + userId);
        return subjectQueueService.getUserInQueue(userId);
    }

    /**
     * This is an endpoint for checking if a student is already in a queue or not
     * @param userId
     * @return a boolean true/false
     */
    @GetMapping("/in-queue/{userId}")
    public boolean userInQueue(@PathVariable("userId") int userId) {
        logger.info("Updating if the user is in queue");
        return subjectQueueService.userInQueue(userId);
    }

    /**
     * This is an endpoint for updating when a student is assigned a student assistance
     * A status will be set to true
     * @param subjectId the subjectId
     * @param userId the userId
     * @return
     */
    @GetMapping("/update/{subjectId}/{userId}")
    public int updateQueue(@PathVariable("subjectId") int subjectId, @PathVariable("userId") int userId) {
        logger.info("Updating student in queue with userId: " + userId+ " from the subject with id: " + subjectId);
        return subjectQueueService.updateQueue(userId, subjectId);
    }
}
