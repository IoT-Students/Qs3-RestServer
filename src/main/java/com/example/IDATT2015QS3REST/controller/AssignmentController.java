package com.example.IDATT2015QS3REST.controller;

import com.example.IDATT2015QS3REST.model.AssignmentApprove;
import com.example.IDATT2015QS3REST.model.LoginRequest;
import com.example.IDATT2015QS3REST.model.LoginResponse;
import com.example.IDATT2015QS3REST.model.User;
import com.example.IDATT2015QS3REST.repository.UserRepository;
import com.example.IDATT2015QS3REST.service.AssignmentApproveService;
import com.example.IDATT2015QS3REST.service.LoginService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/approveAssignment")
@EnableAutoConfiguration
@CrossOrigin
public class AssignmentController {
    @Autowired
    private AssignmentApproveService assignmentApproveService;

    private static final Logger LOGGER = LogManager.getLogger(AssignmentController.class);

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public int doApprovement(final @RequestBody AssignmentApprove assignmentApprove) {
        LOGGER.info("Approving..." + assignmentApprove.getName() + " for the subject "  + assignmentApprove.getSubjectId() + " and assignmentnumber " + assignmentApprove.getAssignmentNumber());

        return assignmentApproveService.doAssignmentApprovment(assignmentApprove);
    }
}
