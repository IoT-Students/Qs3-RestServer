package com.example.IDATT2015QS3REST.service;

import com.example.IDATT2015QS3REST.model.Subject;
import com.example.IDATT2015QS3REST.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        List<Subject> subjectsWithoutSize = new ArrayList<Subject>();
        List<Subject> subjects = new ArrayList<Subject>();

        subjectRepository.getAllSubjects(id).forEach(subjectsWithoutSize::add);
        System.out.println(subjectsWithoutSize.size());

        for (int i = 0; i < subjectsWithoutSize.size(); i++) {
            int queueSize = subjectRepository.getQueueSize(subjectsWithoutSize.get(i).getSubjectId());
            int response = subjectRepository.setQueueSize(subjectsWithoutSize.get(i).getSubjectId(), queueSize);
            System.out.println("Size fra service: " + queueSize);
        }

        subjectRepository.getAllSubjects(id).forEach(subjects::add);
        System.out.println(subjects.size());

        return subjects;
    }
}
