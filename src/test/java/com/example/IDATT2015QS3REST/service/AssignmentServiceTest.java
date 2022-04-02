package com.example.IDATT2015QS3REST.service;

import com.example.IDATT2015QS3REST.model.Assignment;
import com.example.IDATT2015QS3REST.repository.AssignmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class AssignmentServiceTest {
    @InjectMocks
    private AssignmentService assignmentService;

    @Mock
    private AssignmentRepository assignmentRepository;

    @BeforeEach
    public void setUp() {

        Assignment assignment1 = new Assignment(1, 1, 1, false);
        Assignment assignment2 = new Assignment(1,1,2,true);

        ArrayList<Assignment> assignments = new ArrayList<>();
        assignments.add(assignment1);
        assignments.add(assignment2);

        Mockito.lenient().when(assignmentRepository.doAssignmentApprove(Mockito.any())).thenReturn(1);
        Mockito.lenient().when(assignmentRepository.getAllAssignmentsSubject(Mockito.anyInt(), Mockito.anyInt())).thenReturn(assignments);

    }
    @Test
    void doAssignmentApprovment(){
        int response =  assignmentService.doAssignmentApprovment(Mockito.any());

        assertThat(response).isEqualTo(1);

    }
    @Test
    void getAllAssignmentsSubjectTest(){
        List<Assignment> assignments = assignmentService.getAllAssignmentsSubject(Mockito.anyInt(), Mockito.anyInt());

        assertThat(assignments.get(0).getAssignmentId()).isEqualTo(1);
        assertThat(assignments.get(0).getAssignmentNumber()).isEqualTo(1);
        assertThat(assignments.get(0).getSubjectId()).isEqualTo(1);
        assertThat(assignments.get(0).isStatus()).isEqualTo(false);

        assertThat(assignments.get(1).getAssignmentId()).isEqualTo(1);
        assertThat(assignments.get(1).getAssignmentNumber()).isEqualTo(2);
        assertThat(assignments.get(1).getSubjectId()).isEqualTo(1);
        assertThat(assignments.get(1).isStatus()).isEqualTo(true);

    }

}


