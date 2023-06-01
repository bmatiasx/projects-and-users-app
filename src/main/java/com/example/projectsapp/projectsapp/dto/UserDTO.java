package com.example.projectsapp.projectsapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {
    private long id;
    private String name;
    private String email;
    private Set<ProjectDTO> projects;

    public UserDTO(long id, String name, String email, Set<ProjectDTO> projects) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.projects = projects;
    }
}
