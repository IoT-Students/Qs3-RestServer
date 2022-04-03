package com.example.IDATT2015QS3REST.repository;

import com.example.IDATT2015QS3REST.model.SubjectUser;
import com.example.IDATT2015QS3REST.model.User;

import java.util.List;


public interface SubjectStudentRepository {

    int addStudent(String lastName, String name, int subjectId);

    int createStudent(String lastName, String name, String email);

    boolean findUser(String lastName, String name);

    boolean findUserInSubject(String lastName, String name, int subjectid);

    int addTeacher(SubjectUser subjectUser);

    List<User> getUsersSubject(int subjectId);

}
