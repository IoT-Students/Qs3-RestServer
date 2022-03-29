package com.example.IDATT2015QS3REST.controller;

import com.example.IDATT2015QS3REST.model.LoginRequest;
import com.example.IDATT2015QS3REST.model.LoginResponse;
import com.example.IDATT2015QS3REST.model.SubjectUser;
import com.example.IDATT2015QS3REST.model.User;

import com.example.IDATT2015QS3REST.service.SubjectStudentService;
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
@CrossOrigin
public class SubjectStudentController {
    @Autowired
    private SubjectStudentService subjectStudentService;

    private static final Logger LOGGER = LogManager.getLogger(LoginController.class);

    @PostMapping(value = "/saveStudents", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public boolean addUserSubject(@RequestBody List<SubjectUser> subcjectUser) {
        for(int i=0; i<subcjectUser.size(); i++){
            LOGGER.info("Adding..." + subcjectUser.get(i).getUsername() + " to the subject with id: " + subcjectUser.get(i).getSubjectId());
            subjectStudentService.addStudents(new SubjectUser(subcjectUser.get(i).getUsername(),subcjectUser.get(i).getSubjectId()));
        }

        return true;
    }
}
