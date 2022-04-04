package com.example.IDATT2015QS3REST.service;

import com.example.IDATT2015QS3REST.model.SubjectUser;
import com.example.IDATT2015QS3REST.model.User;
import com.example.IDATT2015QS3REST.repository.SubjectStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectStudentService {

    @Autowired
    SubjectStudentRepository subjectStudentRepository;

    public boolean addStudent(SubjectUser subjectUser) {

        String userDetails = subjectUser.getUserDetails();
        String[] details = userDetails.split(",");

        boolean userInSystem = subjectStudentRepository.findUser(details[0], details[1]);

        if (userInSystem) {
            boolean userInSubject = subjectStudentRepository.findUserInSubject(details[0], details[1], subjectUser.getSubjectId());

            if (userInSubject) {
                return false;
            } else {
                subjectStudentRepository.addStudent(details[0], details[1], subjectUser.getSubjectId());
                return true;
            }
        } else {
            subjectStudentRepository.createStudent(details[0], details[1], details[2]);
            subjectStudentRepository.addStudent(details[0], details[1], subjectUser.getSubjectId());
            return true;
        }
    }
    public int addTeacher(SubjectUser subjectUser) {
        return subjectStudentRepository.addTeacher(subjectUser);
    }

    public List<User> getUsersSubject(int subjectId){
        List<User> users = new ArrayList<>();

        subjectStudentRepository.getUsersSubject(subjectId).forEach(users::add);
        System.out.println(users.size());
        return users;

    }
}

