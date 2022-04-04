package com.example.IDATT2015QS3REST.service;

import com.example.IDATT2015QS3REST.model.Subject;
import com.example.IDATT2015QS3REST.model.SubjectUser;
import com.example.IDATT2015QS3REST.model.User;
import com.example.IDATT2015QS3REST.repository.SubjectRepository;
import com.example.IDATT2015QS3REST.repository.SubjectStudentRepository;
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
public class SubjectStudentServiceTest {
    @InjectMocks
    private SubjectStudentService subjectStudentService;

    @Mock
    private SubjectStudentRepository subjectStudentRepository;

    @BeforeEach
    public void setUp() {

        User User1 = new User(1, "name1", "email1", "username", "passw", "role1", "lastname");
        User User2 = new User(2, "name2", "email2", "username", "passw", "role2", "lastname");

        ArrayList<User> subjectUsers = new ArrayList<>();
        subjectUsers.add(User1);
        subjectUsers.add(User2);
        Mockito.lenient().when(subjectStudentRepository.addStudent(Mockito.any(),Mockito.any(),Mockito.anyInt())).thenReturn(1);
        Mockito.lenient().when(subjectStudentRepository.addTeacher(Mockito.any())).thenReturn(1);
        Mockito.lenient().when(subjectStudentRepository.getUsersSubject(Mockito.anyInt())).thenReturn(subjectUsers);
    }
    /*
    @Test
    void addStudentTest(){
        boolean response =  subjectStudentService.addStudent(Mockito.any());

        assertThat(response).isEqualTo(1);
    }

     */
    @Test
    void addTeacherTest(){
        int response =  subjectStudentService.addTeacher(Mockito.any());

        assertThat(response).isEqualTo(1);
    }
    @Test
    void getUsersSubjectTest(){
        List<User> users = subjectStudentService.getUsersSubject(Mockito.anyInt());

        assertThat(users.get(0).getUserID()).isEqualTo(1);
        assertThat(users.get(0).getName()).isEqualTo("name1");
        assertThat(users.get(0).getRole()).isEqualTo("role1");
        assertThat(users.get(0).getEmail()).isEqualTo("email1");

        assertThat(users.get(1).getUserID()).isEqualTo(2);
        assertThat(users.get(1).getName()).isEqualTo("name2");
        assertThat(users.get(1).getRole()).isEqualTo("role2");
        assertThat(users.get(1).getEmail()).isEqualTo("email2");

    }


}
