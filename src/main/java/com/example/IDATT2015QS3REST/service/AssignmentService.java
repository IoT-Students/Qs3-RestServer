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

/**
 * This is a service class for AssignmentService
 */
@Service
public class AssignmentService {
    @Autowired
    AssignmentRepository assignmentRepository;

    /**
     * This is a method for approving an assignment.First the student will be removed from the queue, then the position
     * for all remaining student in the queue will be updated
     * @param assignmentApprove This is an assignementApprove object that hold information needed to approve the correct student
     * @return int
     */
    public int approveAssignment(AssignmentApprove assignmentApprove){
        int response = assignmentRepository.approveAssignment(assignmentApprove);
        assignmentRepository.deleteFromQueue(assignmentApprove);
        assignmentRepository.updatePosition(assignmentApprove);

        return response;
    }

    /**
     * This is a method for fetching all assignments in a subject for a user
     * @param userId The userId for the user
     * @param subjectId The subjectId for the subject
     * @return A list of assignments
     */
    public List<Assignment> getAllAssignmentsSubject(int userId, int subjectId){
        List<Assignment> assignments = new ArrayList<>();

        assignmentRepository.getAllAssignmentsSubject(userId, subjectId).forEach(assignments::add);

        System.out.println(assignments.size());

        return assignments;
    }

    /**
     * A method for a student to leave queue
     * @param assignmentApprove Holds the information it need to remove the correct student
     * @return A status int
     */
    public int leaveQueue(AssignmentApprove assignmentApprove) {
        assignmentRepository.updatePosition(assignmentApprove);
        return assignmentRepository.deleteFromQueue(assignmentApprove);
    }
}
