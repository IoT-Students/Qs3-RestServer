package com.example.IDATT2015QS3REST.service;

import com.example.IDATT2015QS3REST.model.SubjectQueue;
import com.example.IDATT2015QS3REST.model.SubjectQueueJoinObject;
import com.example.IDATT2015QS3REST.repository.SubjectQueueRepository;
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
public class SubjectQueueServiceTest {
    @InjectMocks
    private SubjectQueueService subjectQueueService;

    @Mock
    private SubjectQueueRepository subjectQueueRepository;

    @BeforeEach
    public void setUp() {

        SubjectQueueJoinObject subjectQueueJoinObject1 = new SubjectQueueJoinObject(1, "name", "campus", "building", "room", "table", 1, 1, 1, 1, 1);
        SubjectQueueJoinObject subjectQueueJoinObject2 = new SubjectQueueJoinObject(2, "name", "campus", "building", "room", "table", 2, 0, 2, 1, 2);

        ArrayList<SubjectQueueJoinObject> subjectQueueJoinObjects = new ArrayList<>();
        subjectQueueJoinObjects.add(subjectQueueJoinObject1);
        subjectQueueJoinObjects.add(subjectQueueJoinObject2);

        ArrayList<SubjectQueue> subjectQueueList = new ArrayList<>();
        SubjectQueue subjectQueue = new SubjectQueue(1, "campus", "building", "room", "table", 1, 1, 1, 1);
        subjectQueueList.add(subjectQueue);

        Mockito.lenient().when(subjectQueueRepository.addSubjectQueue(Mockito.any())).thenReturn(1);
        Mockito.lenient().when(subjectQueueRepository.getSubjectQueueUser(Mockito.anyInt(), Mockito.anyInt())).thenReturn(subjectQueueList);
        Mockito.lenient().when(subjectQueueRepository.getAllSubjectQueues(Mockito.anyInt())).thenReturn(subjectQueueJoinObjects);
    }
    @Test
    void addSubjectQueueTest(){
        int response =  subjectQueueService.addSubjectQueue(Mockito.any());

        assertThat(response).isEqualTo(1);
    }
    @Test
    void getAllSubjectQueuesTest(){
        List<SubjectQueueJoinObject> subjectQueueJoinObject = subjectQueueService.getAllSubjectQueues(Mockito.anyInt());

        assertThat(subjectQueueJoinObject.get(0).getSubjectQueueId()).isEqualTo(1);
        assertThat(subjectQueueJoinObject.get(0).getSubjectId()).isEqualTo(1);
        assertThat(subjectQueueJoinObject.get(0).getAssignment()).isEqualTo(1);
        assertThat(subjectQueueJoinObject.get(0).getBuilding()).isEqualTo("building");
        assertThat(subjectQueueJoinObject.get(0).getCampus()).isEqualTo("campus");
        assertThat(subjectQueueJoinObject.get(0).getName()).isEqualTo("name");
        assertThat(subjectQueueJoinObject.get(0).getPosition()).isEqualTo(1);
        assertThat(subjectQueueJoinObject.get(0).getRoom()).isEqualTo("room");
        assertThat(subjectQueueJoinObject.get(0).getTabl()).isEqualTo("table");
        assertThat(subjectQueueJoinObject.get(0).getType()).isEqualTo(1);
        assertThat(subjectQueueJoinObject.get(0).getUserId()).isEqualTo(1);

        assertThat(subjectQueueJoinObject.get(1).getSubjectQueueId()).isEqualTo(2);
        assertThat(subjectQueueJoinObject.get(1).getSubjectId()).isEqualTo(1);
        assertThat(subjectQueueJoinObject.get(1).getAssignment()).isEqualTo(2);
        assertThat(subjectQueueJoinObject.get(1).getBuilding()).isEqualTo("building");
        assertThat(subjectQueueJoinObject.get(1).getCampus()).isEqualTo("campus");
        assertThat(subjectQueueJoinObject.get(1).getName()).isEqualTo("name");
        assertThat(subjectQueueJoinObject.get(1).getPosition()).isEqualTo(2);
        assertThat(subjectQueueJoinObject.get(1).getRoom()).isEqualTo("room");
        assertThat(subjectQueueJoinObject.get(1).getTabl()).isEqualTo("table");
        assertThat(subjectQueueJoinObject.get(1).getType()).isEqualTo(0);
        assertThat(subjectQueueJoinObject.get(1).getUserId()).isEqualTo(2);

    }
    @Test
    void getSubjectQueueUser(){
        List<SubjectQueue> subjectQueue = subjectQueueService.getSubjectQueueUser(Mockito.anyInt(), Mockito.anyInt());

        assertThat(subjectQueue.get(0).getSubjectQueueId()).isEqualTo(1);
        assertThat(subjectQueue.get(0).getSubjectId()).isEqualTo(1);
        assertThat(subjectQueue.get(0).getAssignment()).isEqualTo(1);
        assertThat(subjectQueue.get(0).getBuilding()).isEqualTo("building");
        assertThat(subjectQueue.get(0).getCampus()).isEqualTo("campus");
        assertThat(subjectQueue.get(0).getRoom()).isEqualTo("room");
        assertThat(subjectQueue.get(0).getTabl()).isEqualTo("table");
        assertThat(subjectQueue.get(0).getType()).isEqualTo(1);
        assertThat(subjectQueue.get(0).getUserId()).isEqualTo(1);

    }


}
