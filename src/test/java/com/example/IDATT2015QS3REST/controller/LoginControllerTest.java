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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.*;
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

    @Before
    void doSomethingBeforeAnyTestsAreRun() {
        // Hvis en trenger å gjøre noe i forbindelse med oppsett her (in-mem-base, sette opp objektgrafer, osv. osv.)
    }

    @BeforeEach
    void doSomethingBeforeEveryTest() {
        // Hvis en trenger å kjøre kode, som skal kjøres før hver eneste test (resetting av in-mem-base, f.eks)
    }

    // En har tilsvarende @After og @AfterEach også
    // Se https://www.baeldung.com/junit-before-beforeclass-beforeeach-beforeall for mer om dette

    @Test
    void doLoginTest() throws Exception {

        LoginRequest loginRequest = new LoginRequest("simen2312", "simen2312");
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
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
