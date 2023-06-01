package com.example.projectsapp.projectsapp.unit.service;

import com.example.projectsapp.projectsapp.repository.ProjectRepository;
import com.example.projectsapp.projectsapp.repository.UserRepository;
import com.example.projectsapp.projectsapp.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService service;

    @Test
    public void saveProject_Success() {
        // Given

        // When

        // Then
    }
}
