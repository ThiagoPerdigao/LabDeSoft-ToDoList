package com.labdesoft.roteiro01.integration;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.labdesoft.roteiro01.Roteiro01Application;

import io.restassured.RestAssured;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {Roteiro01Application.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class taskControllerIntegrationTest {

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080; 
    }

    @Test
    public void givenUrl_whenSuccessOnGetsResponseAndJsonHasRequiredKV_thenCorrect() {
        get("/api/tasks").then().statusCode(200);
    }

    @Test
    public void givenUrl_whenSuccessOnGetsResponseAndJsonHasOneTask_thenCorrect() {
        get("/api/tasks/1").then().statusCode(200)
                .assertThat().body("description", equalTo("Primeira tarefa"));
    }
}
