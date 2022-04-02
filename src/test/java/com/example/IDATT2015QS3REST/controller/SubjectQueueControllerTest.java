package com.example.IDATT2015QS3REST.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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


    @Test
    void getAllSubjectQueuesTest() throws Exception {
        // Det er en del forskjellige libs som brukes her, se static imports øverst
        // En har også tilsvarende metoder for POST/PUT/DELETE osv.
        mockMvc.perform(get("/subjectQueue/42").contentType(MediaType.APPLICATION_JSON))
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
                .andExpect(jsonPath("$[0].subjectId", is(42)))
                .andExpect(jsonPath("$[0].position").exists());
        // Merk: jsonPath har litt sær syntaks. Det brukes slik det er nå, fordi at get-kallet som går til /
        // (metoden veryArchitecturalMessage) returnerer en liste med med Meme. Hadde den derimot returnert
        // bare ett objekt, hadde syntaksen i jsonPath endret seg til:
        //    .andExpect(jsonPath("$.*", hasSize(greaterThanOrEqualTo(1))))
        //    .andExpect(jsonPath("$.pic", is("Spongebob")));
        //
        // JsonPath i seg selv er en veldig nyttig sak, men vær obs på at syntaksen kan være kronglete til tider,
        // og en får ikke alltid helt de resultatene en hadde forestilt seg.
    }

    @Test
    void getAllSubjectQueueUserTest() throws Exception {
        // Det er en del forskjellige libs som brukes her, se static imports øverst
        // En har også tilsvarende metoder for POST/PUT/DELETE osv.
        mockMvc.perform(get("/subjectQueue/42/1").contentType(MediaType.APPLICATION_JSON))
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
                .andExpect(jsonPath("$[0].userId", is(1)))
                .andExpect(jsonPath("$[0].subjectId", is(42)))
                .andExpect(jsonPath("$[0].position").exists());

        }


    }
