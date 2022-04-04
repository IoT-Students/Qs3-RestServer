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
class SubjectControllerTest {

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
    /*
    //Denne testen lager nye fag hver gang du kjører
    @Test
    void addSubjectTest() throws Exception {
        // Det er en del forskjellige libs som brukes her, se static imports øverst
        // En har også tilsvarende metoder for POST/PUT/DELETE osv.
        Subject subject = new Subject("subjectCode", "subjectName", 2, 1);

        mockMvc.perform(MockMvcRequestBuilders.post("/subject")
                        .content(objectMapper.writeValueAsString(subject))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.subjectId").exists());
    }

     */
    @Test
    void getAllSubjectsTest() throws Exception {
        // Det er en del forskjellige libs som brukes her, se static imports øverst
        // En har også tilsvarende metoder for POST/PUT/DELETE osv.
        mockMvc.perform(get("/subject/1/").header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))))
                .andExpect(jsonPath("$[0].subjectId", is(42)))
                .andExpect(jsonPath("$[0].subjectCode", is("IDATT2105")))
                .andExpect(jsonPath("$[0].subjectName", is("Fullstack")))
                .andExpect(jsonPath("$[0].assignmentAmount", is(7)))
                .andExpect(jsonPath("$[0].requiredAssignments", is(6)))
                .andExpect(jsonPath("$[0].queueSize").exists());
    }
}
