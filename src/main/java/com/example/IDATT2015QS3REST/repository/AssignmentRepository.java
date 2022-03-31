package com.example.IDATT2015QS3REST.repository;

import com.example.IDATT2015QS3REST.model.Assignment;
import com.example.IDATT2015QS3REST.model.AssignmentApprove;

import java.util.List;


public interface AssignmentRepository {
    int doAssignmentApprove(AssignmentApprove assignmentApprove);

    List<Assignment> getAllAssignmentsSubject(int userId, int subjectId);
}
