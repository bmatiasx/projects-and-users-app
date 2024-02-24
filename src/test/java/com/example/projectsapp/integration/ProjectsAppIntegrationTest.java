package com.example.projectsapp.integration;

import com.example.projectsapp.model.Project;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql({"/schema.sql", "/data.sql"})
public class ProjectsAppIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetProjectById() {
        // TODO fix test
//        ResponseEntity<Project> responseEntity = restTemplate.exchange("/api/v1/projects/1", HttpMethod.GET, null, Project.class);
//
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
