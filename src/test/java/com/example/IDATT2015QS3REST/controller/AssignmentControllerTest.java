package com.example.IDATT2015QS3REST.controller;

import ch.qos.logback.core.subst.Token;
import com.example.IDATT2015QS3REST.model.LoginRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
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
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class) // JUnit
//@SpringBootTest(webEnvironment = MOCK, classes = TestsDemoApplication.class) // Spring
@SpringBootTest
@AutoConfigureMockMvc // Trengs for å kunne autowire MockMvc
class AssignmentControllerTest {
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
    void getAllAssignmentsSubject() throws Exception {
        // Det er en del forskjellige libs som brukes her, se static imports øverst
        // En har også tilsvarende metoder for POST/PUT/DELETE osv.
        mockMvc.perform(get("/assignment/1/42").header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", hasSize(greaterThanOrEqualTo(1))))
                .andExpect(jsonPath("$[0].assignmentId").exists())
                .andExpect(jsonPath("$[0].subjectId").exists())
                .andExpect(jsonPath("$[0].assignmentNumber").exists())
                .andExpect(jsonPath("$[0].status").exists());

        // Merk: jsonPath har litt sær syntaks. Det brukes slik det er nå, fordi at get-kallet som går til /
        // (metoden veryArchitecturalMessage) returnerer en liste med med Meme. Hadde den derimot returnert
        // bare ett objekt, hadde syntaksen i jsonPath endret seg til:
        //    .andExpect(jsonPath("$.*", hasSize(greaterThanOrEqualTo(1))))
        //    .andExpect(jsonPath("$.pic", is("Spongebob")));
        //
        // JsonPath i seg selv er en veldig nyttig sak, men vær obs på at syntaksen kan være kronglete til tider,
        // og en får ikke alltid helt de resultatene en hadde forestilt seg.
    }
}
