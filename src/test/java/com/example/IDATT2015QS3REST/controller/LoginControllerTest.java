package com.example.IDATT2015QS3REST.controller;


import com.example.IDATT2015QS3REST.model.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class) // JUnit
//@SpringBootTest(webEnvironment = MOCK, classes = TestsDemoApplication.class) // Spring
@SpringBootTest
@AutoConfigureMockMvc // Trengs for å kunne autowire MockMvc
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    public String token;
    @BeforeEach
    void doSomethingBeforeEveryTest() throws Exception {
        LoginRequest loginRequest = new LoginRequest("usernameTEST", "passwordTEST");

        MvcResult result = mockMvc.perform(post("http://localhost:8085/token")
                        .content(objectMapper.writeValueAsString(loginRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();

        String response = result.getResponse().getContentAsString();
        String[] array = response.split(",");
        String[] array2 = array[5].split(":");
        token = array2[1].substring(0,array2[1].length()-1);
        token= token.substring(1, token.length() - 1);
    }

    @Test
    void doLoginTest() throws Exception {

        LoginRequest loginRequest = new LoginRequest("simen2312", "simen2312");
        mockMvc.perform(MockMvcRequestBuilders.post("/login").header("Authorization", "Bearer " + token)
                        .content(objectMapper.writeValueAsString(loginRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.*", hasSize(greaterThanOrEqualTo(1))))
                .andExpect(MockMvcResultMatchers.jsonPath("$.loginStatus", is("Success")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userID", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.role", is("Student")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("Simen")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", is("simenk2312@gmail.com")));


    }
}
