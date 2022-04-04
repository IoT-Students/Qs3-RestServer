package com.example.IDATT2015QS3REST.controller;

import com.example.IDATT2015QS3REST.model.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class) // JUnit
//@SpringBootTest(webEnvironment = MOCK, classes = TestsDemoApplication.class) // Spring
@SpringBootTest
@AutoConfigureMockMvc // Trengs for å kunne autowire MockMvc
class SubjectQueueControllerTest {

    @Autowired
    private MockMvc mockMvc;  // Krever at @AutoConfigureMockMvc er satt, brukes til å fyre av requests

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
    void getAllSubjectQueuesTest() throws Exception {
        // Det er en del forskjellige libs som brukes her, se static imports øverst
        // En har også tilsvarende metoder for POST/PUT/DELETE osv.
        mockMvc.perform(get("/subjectQueue/10000")
                .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", hasSize(greaterThanOrEqualTo(1))))
                .andExpect(jsonPath("$[0].subjectQueueId").exists())
                .andExpect(jsonPath("$[0].name").exists())
                .andExpect(jsonPath("$[0].campus").exists())
                .andExpect(jsonPath("$[0].building").exists())
                .andExpect(jsonPath("$[0].room").exists())
                .andExpect(jsonPath("$[0].tabl").exists())
                .andExpect(jsonPath("$[0].assignment").exists())
                .andExpect(jsonPath("$[0].type").exists())
                .andExpect(jsonPath("$[0].userId").exists())
                .andExpect(jsonPath("$[0].subjectId", is(10000)))
                .andExpect(jsonPath("$[0].position").exists())
                .andExpect(jsonPath("$[0].status").exists());
    }

    @Test
    void getAllSubjectQueueUserTest() throws Exception {
        // Det er en del forskjellige libs som brukes her, se static imports øverst
        // En har også tilsvarende metoder for POST/PUT/DELETE osv.
        mockMvc.perform(get("/subjectQueue/10000/10000")
                        .header("Authorization", "Bearer " + token).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", hasSize(greaterThanOrEqualTo(1))))
                .andExpect(jsonPath("$[0].subjectQueueId").exists())
                .andExpect(jsonPath("$[0].campus").exists())
                .andExpect(jsonPath("$[0].building").exists())
                .andExpect(jsonPath("$[0].room").exists())
                .andExpect(jsonPath("$[0].tabl").exists())
                .andExpect(jsonPath("$[0].assignment").exists())
                .andExpect(jsonPath("$[0].type").exists())
                .andExpect(jsonPath("$[0].userId", is(10000)))
                .andExpect(jsonPath("$[0].subjectId", is(10000)))
                .andExpect(jsonPath("$[0].position").exists());

        }


    }
