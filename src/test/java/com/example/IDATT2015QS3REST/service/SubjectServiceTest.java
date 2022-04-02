package com.example.IDATT2015QS3REST.service;

import com.example.IDATT2015QS3REST.model.Subject;
import com.example.IDATT2015QS3REST.repository.SubjectRepository;
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
public class SubjectServiceTest {
    @InjectMocks
    private SubjectService subjectService;

    @Mock
    private SubjectRepository subjectRepository;

    @BeforeEach
    public void setUp() {

        Subject subject1 = new Subject(1,"code", "name", 2, 1, 1);
        Subject subject2 = new Subject(2,"code", "name", 3, 2, 2);

        ArrayList<Subject> subjects = new ArrayList<>();
        subjects.add(subject1);
        subjects.add(subject2);
        Mockito.lenient().when(subjectRepository.addSubject(Mockito.any())).thenReturn(1);
        Mockito.lenient().when(subjectRepository.getAllSubjects(Mockito.anyInt())).thenReturn(subjects);
    }
    @Test
    void addSubjectTest(){
        int response =  subjectService.addSubject(Mockito.any());

        assertThat(response).isEqualTo(1);
    }
    @Test
    void getAllSubjectsTest() {
        List<Subject> subjects = subjectService.getAllSubjects(Mockito.anyInt());

        assertThat(subjects.get(0).getSubjectId()).isEqualTo(1);
        assertThat(subjects.get(0).getSubjectCode()).isEqualTo("code");
        assertThat(subjects.get(0).getSubjectName()).isEqualTo("name");
        assertThat(subjects.get(0).getAssignmentAmount()).isEqualTo(2);
        assertThat(subjects.get(0).getRequiredAssignments()).isEqualTo(1);
        assertThat(subjects.get(0).getQueueSize()).isEqualTo(1);


        assertThat(subjects.get(1).getSubjectId()).isEqualTo(2);
        assertThat(subjects.get(1).getSubjectCode()).isEqualTo("code");
        assertThat(subjects.get(1).getSubjectName()).isEqualTo("name");
        assertThat(subjects.get(1).getAssignmentAmount()).isEqualTo(3);
        assertThat(subjects.get(1).getRequiredAssignments()).isEqualTo(2);
        assertThat(subjects.get(1).getQueueSize()).isEqualTo(2);
    }

}
