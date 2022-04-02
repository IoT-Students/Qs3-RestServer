package com.example.IDATT2015QS3REST.service;

import com.example.IDATT2015QS3REST.model.Subject;

import com.example.IDATT2015QS3REST.repository.UserRepository;
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
public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {

        Mockito.lenient().when(userRepository.save(Mockito.any())).thenReturn(1);

    }
    @Test
    void saveUserTest(){
        int response =  userService.saveUser(Mockito.any());

        assertThat(response).isEqualTo(1);
    }
}
