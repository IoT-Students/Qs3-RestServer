package com.example.IDATT2015QS3REST.repository;

import com.example.IDATT2015QS3REST.model.SubjectUser;


public interface SubjectStudentRepository {

    int addStudent(SubjectUser subjectUser);
    int addTeacher(SubjectUser subjectUser);

}
