package com.example.IDATT2015QS3REST.service;

import com.example.IDATT2015QS3REST.model.LoginRequest;
import com.example.IDATT2015QS3REST.model.LoginResponse;
import com.example.IDATT2015QS3REST.repository.LoginRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {

    @InjectMocks
    private LoginService loginService;

    @Mock
    private LoginRepository loginRepository;

    @BeforeEach
    public void setUp() {
        LoginResponse loginResponse = new LoginResponse("Success", 1,  "Student", "Name", "email.com", "lastname");

        Mockito.lenient().when(loginRepository.findByLoginRequest(Mockito.any())).thenReturn(loginResponse);
    }

    @Test
    void loginResponseTest() {
        LoginResponse loginResponse = loginService.doLoginRequest(Mockito.any());

        assertThat(loginResponse.getUserID()).isEqualTo(1);
        assertThat(loginResponse.getLoginStatus()).isEqualTo("Success");
        assertThat(loginResponse.getEmail()).isEqualTo("email.com");
        assertThat(loginResponse.getName()).isEqualTo("Name");
        assertThat(loginResponse.getRole()).isEqualTo("Student");
    }

}
