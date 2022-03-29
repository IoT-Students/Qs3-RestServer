package com.example.IDATT2015QS3REST.service;

import com.example.IDATT2015QS3REST.model.Subject;
import com.example.IDATT2015QS3REST.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectService {

    @Autowired
    SubjectRepository subjectRepository;

    public int addSubject(Subject subject) {
        return subjectRepository.addSubject(subject);
    }


    public List<Subject> getAllSubjects(int id){
        List<Subject> subjects = new ArrayList<Subject>();

        subjectRepository.getAllSubjects(id).forEach(subjects::add);

        System.out.println(subjects.size());

        return subjects;



    }
}
