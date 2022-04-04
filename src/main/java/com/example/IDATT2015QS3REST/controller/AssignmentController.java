package com.example.IDATT2015QS3REST.controller;

import com.example.IDATT2015QS3REST.model.AssignmentApprove;
import com.example.IDATT2015QS3REST.model.SubjectQueue;
import com.example.IDATT2015QS3REST.service.AssignmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is a controller class for assignments. For approving, fetching and deleting assignment.
 */

@RestController
@RequestMapping(value = "/assignment")
@EnableAutoConfiguration
@CrossOrigin
@Api(value = "Assignment bdskfs", description = "heiogh")
public class AssignmentController {
    @Autowired
    private AssignmentService assignmentApproveService;

    private static final Logger LOGGER = LogManager.getLogger(AssignmentController.class);

    /**
     * This is an endpoint for approving an assignment and removing the student from the queue
     * AssignmentApprove is an object that store userId, subjectId, assignmentNumber, position
     * This is all needed for approving the right person and deleting the right person from queue
     * @param assignmentApprove The assignment to be approved
     * @return
     */
    @ApiOperation(value = "Approves assignment")
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public int approveAssignment(final @RequestBody AssignmentApprove assignmentApprove) {
        LOGGER.info("Approving..." + assignmentApprove.getUserId() + " for the subject "  + assignmentApprove.getSubjectId() + " and assignmentnumber " + assignmentApprove.getAssignmentNumber());
        return assignmentApproveService.approveAssignment(assignmentApprove);
    }

    /**
     * This is an endpoint for removing a student from the queue
     * @param assignmentApprove
     * @return
     */
    @PostMapping("/leave-queue")
    public int leaveQueue(@RequestBody AssignmentApprove assignmentApprove) {
        LOGGER.info("Removing user with ID: " + assignmentApprove.getUserId() + " from the queue for the subject: " + assignmentApprove.getSubjectId());
        return assignmentApproveService.leaveQueue(assignmentApprove);
    }

    /**
     * This is an endpoint for fetching all the assignments for a user in a subject
     * @param userId The userId to the user trying to fetch the assignments
     * @param subjectId The subjectId to the user trying to fetch the assignments
     * @return
     */

    @GetMapping("{userId}/{subjectId}")
    public List getAllAssignmentsSubject(@PathVariable("userId") int userId, @PathVariable("subjectId") int subjectId ){
        LOGGER.info("Fetching all the assignment for a student in a subject...");
        return assignmentApproveService.getAllAssignmentsSubject(userId, subjectId);
    }

}
