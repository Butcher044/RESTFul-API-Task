package com.example.restfulapiproject;

import com.example.restfulapiproject.model.dto.TaskDTO;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(classes = RestfulApiProjectApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskControllerTest {

    @LocalServerPort
    private final int randomServerPort;

    private Integer createdTaskId;

    public TaskControllerTest(@LocalServerPort int randomServerPort) {
        this.randomServerPort = randomServerPort;
    }

    @BeforeEach
    @Transactional(readOnly = true)
    public void setUp() {
        RestAssured.port = randomServerPort;

        // Создание задачи в базе данных перед выполнением теста
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle("Test Task");
        taskDTO.setDescription("Test Description");
        taskDTO.setCompleted(false);

        createdTaskId = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(taskDTO)
                .when()
                .post("/api/tasks")
                .then()
                .statusCode(201)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body("id", notNullValue())
                .extract()
                .path("id");
    }

    @AfterEach
    @Transactional
    public void tearDown() {
        // Удаление созданной задачи после выполнения теста
        given()
                .when()
                .delete("/api/tasks/{id}", createdTaskId)
                .then()
                .statusCode(204);
    }

    @Test
    @Transactional(readOnly = true)
    public void testGetById() {
        given()
                .auth().basic("user", "password")
                .when()
                .get("/api/tasks/{id}", createdTaskId)
                .then()
                .log().all()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body("id", equalTo(createdTaskId))
                .body("title", equalTo("Test Task"))
                .body("description", equalTo("Test Description"))
                .body("completed", equalTo(false));
    }

    @Test
    @Transactional(readOnly = true)
    public void testCreateOrUpdate() {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle("Sample Title");
        taskDTO.setDescription("Sample Description");
        taskDTO.setCompleted(false);

        given()
                .auth().basic("user", "password")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(taskDTO)
                .when()
                .post("/api/tasks")
                .then()
                .statusCode(201)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body("id", notNullValue());
    }

    @Test
    @Transactional
    public void testDeleteById() {
        given()
                .auth().basic("user", "password")
                .when()
                .delete("/api/tasks/{id}", createdTaskId)
                .then()
                .statusCode(204);
    }

}
