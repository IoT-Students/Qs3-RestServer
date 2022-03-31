package com.example.IDATT2015QS3REST.service;

import com.example.IDATT2015QS3REST.model.AssignmentApprove;
import com.example.IDATT2015QS3REST.repository.AssignmentApproveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssignmentApproveService {
    @Autowired
    AssignmentApproveRepository assignmentApproveRepository;

    public int doAssignmentApprovment(AssignmentApprove assignmentApprove){
        return assignmentApproveRepository.doAssignmentApprove(assignmentApprove);
    }
}
