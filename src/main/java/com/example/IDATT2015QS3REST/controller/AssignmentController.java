package com.example.IDATT2015QS3REST.controller;

import com.example.IDATT2015QS3REST.model.AssignmentApprove;
import com.example.IDATT2015QS3REST.service.AssignmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/assignment")
@EnableAutoConfiguration
@CrossOrigin
public class AssignmentController {
    @Autowired
    private AssignmentService assignmentApproveService;

    private static final Logger LOGGER = LogManager.getLogger(AssignmentController.class);

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public int doApprovement(final @RequestBody AssignmentApprove assignmentApprove) {
        LOGGER.info("Approving..." + assignmentApprove.getName() + " for the subject "  + assignmentApprove.getSubjectId() + " and assignmentnumber " + assignmentApprove.getAssignmentNumber());

        return assignmentApproveService.doAssignmentApprovment(assignmentApprove);
    }

    @GetMapping("{userId}/{subjectId}")
    public List getAllAssignmentsSubject(@PathVariable("userId") int userId, @PathVariable("subjectId") int subjectId ){
        LOGGER.info("Jeg prøver å hente ut alle øvinger til en student på et fag");
        return assignmentApproveService.getAllAssignmentsSubject(userId, subjectId);
    }

}
