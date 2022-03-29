package com.example.IDATT2015QS3REST.repository;

import com.example.IDATT2015QS3REST.model.Subject;

import java.util.List;

public interface SubjectRepository {
    int addSubject(Subject subject);

    List<Subject> getAllSubjects(int id);
}
