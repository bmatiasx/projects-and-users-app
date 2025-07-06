package com.example.projectsapp.integration;

import com.example.projectsapp.dto.ProjectDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ProjectsAppIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetProjectById() {
        ResponseEntity<ProjectDTO> responseEntity = restTemplate
                .exchange("/v1/projects/1", HttpMethod.GET, null, ProjectDTO.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
