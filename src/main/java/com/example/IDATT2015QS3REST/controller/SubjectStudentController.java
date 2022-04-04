package com.example.IDATT2015QS3REST.controller;

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

/**
 * This is a controller class for adding students, teacher or
 * student assistance to a subject
 */

@RequestMapping("/subject/students")
@RestController
@EnableAutoConfiguration
@CrossOrigin
public class SubjectStudentController {
    @Autowired
    private SubjectStudentService subjectStudentService;

    private static final Logger LOGGER = LogManager.getLogger(SubjectStudentController.class);

    /**
     * This is an endpoint for creating and adding students to a subject, or just adding students
     * if the student is already in the system
     * @param subjectUser A subjectUser which holds userDetails and subjectId
     * @return A boolean
     */
    @PostMapping(value = "/saveStudents", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public boolean addUserListSubject(@RequestBody List<SubjectUser> subjectUser) {
        for(int i=0; i<subjectUser.size(); i++){
            LOGGER.info("Adding user" + subjectUser.get(i).getUserDetails() + " to the subject with id: " + subjectUser.get(i).getSubjectId());
            subjectStudentService.addStudent(new SubjectUser(subjectUser.get(i).getUserDetails(),subjectUser.get(i).getSubjectId()));
        }

        return true;
    }

    /**
     * This is an endpoint for adding a teacher to a subject
     * @param subjectUser A subjectUser which holds userDetails and subjectId
     * @return A boolean
     */
    @PostMapping(value = "/saveTeacherSubject", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public boolean addTeacherSubject(@RequestBody SubjectUser subjectUser) {
        LOGGER.info("Adding teacher " + subjectUser.getUserDetails() + " to the subject with id: " + subjectUser.getSubjectId());
        subjectStudentService.addTeacher(new SubjectUser(subjectUser.getUserDetails(),subjectUser.getSubjectId()));

        return true;
    }

    @PostMapping(value = "/saveStudassSubject", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public boolean addStudassSubject(@RequestBody List<SubjectUser> subjectUser) {
        for(int i=0; i <subjectUser.size(); i++ ){
            LOGGER.info("Adding student assistance " + subjectUser.get(i).getUserDetails() + " to the subject with id: " + subjectUser.get(i).getSubjectId());
            subjectStudentService.addTeacher(new SubjectUser(subjectUser.get(i).getUserDetails(),subjectUser.get(i).getSubjectId()));
        }

        return true;
    }

    /**
     * This is an endpint for fetching all the students and student assistance for a subject
     * @param subjectId The subjectId
     * @return A list with users
     */
    @GetMapping("{subjectId}")
    public List<User> getUsersSubject(@PathVariable("subjectId") int subjectId){
        LOGGER.info("Fetching all the students/stuass for a subject with id: " + subjectId);
        return subjectStudentService.getUsersSubject(subjectId);
    }
}
