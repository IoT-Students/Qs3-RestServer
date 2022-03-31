package com.example.IDATT2015QS3REST.service;

import com.example.IDATT2015QS3REST.model.Assignment;
import com.example.IDATT2015QS3REST.model.AssignmentApprove;
import com.example.IDATT2015QS3REST.model.Subject;
import com.example.IDATT2015QS3REST.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssignmentService {
    @Autowired
    AssignmentRepository assignmentRepository;

    public int doAssignmentApprovment(AssignmentApprove assignmentApprove){
        return assignmentRepository.doAssignmentApprove(assignmentApprove);
    }

    public List<Assignment> getAllAssignmentsSubject(int userId, int subjectId){
        List<Assignment> assignments = new ArrayList<>();

        assignmentRepository.getAllAssignmentsSubject(userId, subjectId).forEach(assignments::add);

        System.out.println(assignments.size());

        return assignments;



    }
}
