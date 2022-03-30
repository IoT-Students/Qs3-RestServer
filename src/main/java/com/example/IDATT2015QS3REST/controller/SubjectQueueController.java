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
        return subjectQueueService.addSubjectQueue(subjectQueue);
    }

    @GetMapping("{subjectId}")
    public List getAllSubjectQueues(@PathVariable("subjectId") int subjectId){
        logger.info("Jeg prøver å hente ut alle subjectQueues objekter til et fag");
        return subjectQueueService.getAllSubjectQueues(subjectId);
    }

    @GetMapping("{subjectId}/{userId}")
    public List getSubjectQueueUser(@PathVariable("subjectId") int subjectId, @PathVariable("userId") int userId){
        logger.info("Jeg prøver å hente ut køen til en bestemt user med subjectId: " + subjectId + "og userId: " + userId);
        return subjectQueueService.getSubjectQueueUser(subjectId, userId);
    }


}