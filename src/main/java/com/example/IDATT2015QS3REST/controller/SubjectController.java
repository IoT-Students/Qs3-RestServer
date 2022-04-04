package com.example.IDATT2015QS3REST.controller;

import  com.example.IDATT2015QS3REST.model.LoginRequest;
import com.example.IDATT2015QS3REST.model.LoginResponse;
import com.example.IDATT2015QS3REST.model.Subject;
import com.example.IDATT2015QS3REST.service.LoginService;
import com.example.IDATT2015QS3REST.service.SubjectService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is a controller class for the subjects. It is used
 * for adding new subjects, and fetching the already created subjects
 */

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/subject")
@CrossOrigin
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    private static final Logger LOGGER = LogManager.getLogger(SubjectController.class);

    /**
     * This is an endpoint for adding a subject. A subject needs a code, name, total amount of assignments,
     * required assignments. A subjectId will be auto generated, and the queue size is set to zero.
     * @param subject The subject that is added to the database
     * @return
     */
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public int addSubject(final @RequestBody Subject subject) {
        LOGGER.info("Adding subject: " + subject.getSubjectName() + " with code: " + subject.getSubjectCode());

        return subjectService.addSubject(subject);
    }

    /**
     * This is an endpoint for fetching all the subjects to a user. It is used for students, teachers
     * and student assistance
     * @param id the userId
     * @return
     */
    @GetMapping("{id}")
    public List getAllSubjects(@PathVariable("id") int id){
        LOGGER.info("Fetching all the subjects to the user with id: " + id);
        return subjectService.getAllSubjects(id);
    }
}
