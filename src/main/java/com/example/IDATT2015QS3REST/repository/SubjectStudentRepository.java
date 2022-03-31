package com.example.IDATT2015QS3REST.repository;

import com.example.IDATT2015QS3REST.model.SubjectUser;
import com.example.IDATT2015QS3REST.model.User;

import java.util.List;


public interface SubjectStudentRepository {

    int addStudents(SubjectUser subjectUser);

    List<User> getUsersSubject(int subjectId);

}
