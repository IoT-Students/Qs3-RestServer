package com.example.IDATT2015QS3REST.service;

import com.example.IDATT2015QS3REST.model.Subject;
import com.example.IDATT2015QS3REST.model.SubjectQueue;
import com.example.IDATT2015QS3REST.repository.SubjectQueueRepository;
import com.example.IDATT2015QS3REST.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectQueueService {
    @Autowired
    SubjectQueueRepository subjectQueueRepository;

    public int addSubjectQueue(SubjectQueue subjectQueue) {
        return subjectQueueRepository.addSubjectQueue(subjectQueue);
    }

}
