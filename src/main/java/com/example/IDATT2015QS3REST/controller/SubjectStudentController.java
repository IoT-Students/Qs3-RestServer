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

@RequestMapping("/subject/students")
@RestController
@EnableAutoConfiguration
@CrossOrigin
public class SubjectStudentController {
    @Autowired
    private SubjectStudentService subjectStudentService;

    private static final Logger LOGGER = LogManager.getLogger(LoginController.class);

    @PostMapping(value = "/saveStudents", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public boolean addUserListSubject(@RequestBody List<SubjectUser> subjectUser) {
        for(int i=0; i<subjectUser.size(); i++){
            LOGGER.info("Adding..." + subjectUser.get(i).getName() + " to the subject with id: " + subjectUser.get(i).getSubjectId());
            subjectStudentService.addStudent(new SubjectUser(subjectUser.get(i).getName(),subjectUser.get(i).getSubjectId()));
        }

        return true;
    }
    @PostMapping(value = "/saveTeacherSubject", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public boolean addTeacherSubject(@RequestBody SubjectUser subjectUser) {
        LOGGER.info("Adding..." + subjectUser.getName() + " to the subject with id: " + subjectUser.getSubjectId());
        subjectStudentService.addTeacher(new SubjectUser(subjectUser.getName(),subjectUser.getSubjectId()));

        return true;
    }

    @GetMapping("{subjectId}")
    public List<User> getUsersSubject(@PathVariable("subjectId") int subjectId){
        LOGGER.info("Jeg henter ut alle studenter på et faget: " + subjectId);
        return subjectStudentService.getUsersSubject(subjectId);
    }
}
