package com.example.IDATT2015QS3REST.service;

import com.example.IDATT2015QS3REST.model.Assignment;
import com.example.IDATT2015QS3REST.model.AssignmentApprove;
import com.example.IDATT2015QS3REST.model.Subject;
import com.example.IDATT2015QS3REST.model.SubjectQueue;
import com.example.IDATT2015QS3REST.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssignmentService {
    @Autowired
    AssignmentRepository assignmentRepository;

    public int approveAssignment(AssignmentApprove assignmentApprove){
        int response = assignmentRepository.approveAssignment(assignmentApprove);
        assignmentRepository.deleteFromQueue(assignmentApprove);
        assignmentRepository.updatePosition(assignmentApprove);

        return response;
    }

    public List<Assignment> getAllAssignmentsSubject(int userId, int subjectId){
        List<Assignment> assignments = new ArrayList<>();

        assignmentRepository.getAllAssignmentsSubject(userId, subjectId).forEach(assignments::add);

        System.out.println(assignments.size());

        return assignments;
    }

    public int leaveQueue(AssignmentApprove assignmentApprove) {
        assignmentRepository.updatePosition(assignmentApprove);
        return assignmentRepository.deleteFromQueue(assignmentApprove);
    }
}
