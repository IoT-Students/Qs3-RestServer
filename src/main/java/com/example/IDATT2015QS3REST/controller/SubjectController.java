package com.example.IDATT2015QS3REST.controller;

import com.example.IDATT2015QS3REST.model.LoginRequest;
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

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/subject")
@CrossOrigin
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    private static final Logger LOGGER = LogManager.getLogger(LoginController.class);

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public int addSubject(final @RequestBody Subject subject) {
        LOGGER.info("Adding subject..." + subject.getSubjectCode() + subject.getRequiredAssignments());

        return subjectService.addSubject(subject);
    }
    @GetMapping("{id}")
    public List getAllSubjects(@PathVariable("id") int id){
        LOGGER.info("Jeg prøver å hente ut alle fag til en bruker");
        return subjectService.getAllSubjects(id);

    }
}
