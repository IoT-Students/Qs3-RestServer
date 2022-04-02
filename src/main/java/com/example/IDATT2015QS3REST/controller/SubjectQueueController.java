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
    public int addSubjectQueue(@RequestBody SubjectQueue subjectQueue) {
        logger.info("DETTE ER ASSIGNMENTNUMMER:" + subjectQueue.getAssignment() + " DETTE ER DET ANDRE" + subjectQueue.getSubjectId() + subjectQueue.getBuilding());
        return subjectQueueService.addSubjectQueue(subjectQueue);
    }

    @GetMapping("/{subjectId}")
    public List getAllSubjectQueues(@PathVariable("subjectId") int subjectId){
        return subjectQueueService.getAllSubjectQueues(subjectId);
    }

    @GetMapping("/{subjectId}/{userId}")
    public List getSubjectQueueUser(@PathVariable("subjectId") int subjectId, @PathVariable("userId") int userId){
        logger.info("Prøver å hente subjectQueueUser med subjectId " + subjectId + " og userId " + userId);
        return subjectQueueService.getSubjectQueueUser(subjectId, userId);
    }

    @GetMapping("/queue/{userId}")
    public List getUserInQueue(@PathVariable("userId") int userId) {
        logger.info("Henter bruker i kø med id " + userId);
        return subjectQueueService.getUserInQueue(userId);
    }

    @GetMapping("/in-queue/{userId}")
    public boolean userInQueue(@PathVariable("userId") int userId) {
        logger.info("Henter ut om brukeren er i kø");
        return subjectQueueService.userInQueue(userId);
    }

    @PostMapping("/leave-queue")
    public int leaveQueue(@RequestBody SubjectQueue subjectQueue) {
        logger.info("Fjerner bruker " + subjectQueue.getUserId() + " fra kø " + subjectQueue.getSubjectId());
        return subjectQueueService.leaveQueue(subjectQueue);
    }
}
