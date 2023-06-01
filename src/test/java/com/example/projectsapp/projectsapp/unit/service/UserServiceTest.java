package com.example.projectsapp.projectsapp.unit.service;

import com.example.projectsapp.projectsapp.exception.UserEmailExistsException;
import com.example.projectsapp.projectsapp.exception.UserEmailNotFoundException;
import com.example.projectsapp.projectsapp.exception.UserEmailNotValidException;
import com.example.projectsapp.projectsapp.exception.UserFieldNotValidException;
import com.example.projectsapp.projectsapp.exception.UserIdNotFoundException;
import com.example.projectsapp.projectsapp.exception.UserNameNotFoundException;
import com.example.projectsapp.projectsapp.exception.UsersNotCreatedException;
import com.example.projectsapp.projectsapp.model.Project;
import com.example.projectsapp.projectsapp.model.User;
import com.example.projectsapp.projectsapp.repository.UserRepository;
import com.example.projectsapp.projectsapp.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service;

    @Test
    public void saveUser_Success() {
        // Given
        var user = new User();
        user.setId(1L);
        user.setName("Charles");
        user.setEmail("cgillian@ymail.com");

        // When
        when(repository.save(any(User.class))).thenReturn(user);

        // Then
        User savedUser = service.create(user);
        assertThat(savedUser.getName()).isNotNull();
    }

    @Test
    public void saveUser_notValidEmail_Failed() {
        var user = new User();
        user.setId(1L);
        user.setName("Charles");
        user.setEmail("cgillianymail.com");

        Exception e = assertThrows(UserEmailNotValidException.class, () -> service.create(user));

        var want = "email format is not valid";
        var got = e.getMessage();

        assertTrue(got.contains(want));
    }

    @Test
    public void saveUser_userExists_Failed() {
        // Given
        var user = new User();
        user.setId(1L);
        user.setName("Charles");
        user.setEmail("cgillian@ymail.com");

        // When
        when(repository.findUserByEmail(any(String.class))).thenReturn(Optional.of(user));

        // Then
        Exception e = assertThrows(UserEmailExistsException.class, () -> service.create(user));

        var want = "given email already exists";
        var got = e.getMessage();

        assertTrue(got.contains(want));
    }

    @Test
    public void saveUser_blankEmail_Failed() {
        // Given
        var user = new User();
        user.setId(1L);
        user.setName("Charles");
        user.setEmail("");

        // Then
        Exception e = assertThrows(UserEmailNotValidException.class, () -> service.create(user));

        var want = "email format is not valid";
        var got = e.getMessage();

        assertTrue(got.contains(want));
    }

    @Test
    public void saveUser_blankName_Failed() {
        // Given
        var user = new User();
        user.setId(1L);
        user.setName("");
        user.setEmail("cgi@yahoo.com");

        // Then
        Exception e = assertThrows(UserFieldNotValidException.class, () -> service.create(user));

        var want = "field name is not valid";
        var got = e.getMessage();

        assertTrue(got.contains(want));
    }

    @Test
    public void findAll_Success() {
        // Given
        var user1 = new User();
        user1.setId(1L);
        user1.setName("Charles");
        user1.setEmail("cgillian@ymail.com");

        var user2 = new User();
        user2.setId(2L);
        user2.setName("Hanna");
        user2.setEmail("hbeckam@gmail.com");

        var userList = Arrays.asList(user1, user2);
        Pageable pageable = Pageable.ofSize(10).withPage(0);
        Page<User> expectedPage = new PageImpl<>(userList, pageable, userList.size());

        // When
        when(repository.findAll(pageable)).thenReturn(expectedPage);

        // Then
        Page<User> result = service.findAll(pageable);

        verify(repository).findAll(pageable);

        assertEquals(expectedPage, result);
    }

    @Test
    public void findAll_Failed() {
        // Given
        var pageable = Pageable.ofSize(10).withPage(0);

        // When
        when(repository.findAll(pageable)).thenReturn(Page.empty());

        Exception e = assertThrows(UsersNotCreatedException.class, () -> service.findAll(pageable));

        // Then
        verify(repository).findAll(pageable);

        var want = "no users created";
        var got = e.getMessage();

        assertTrue(got.contains(want));
    }

    @Test
    public void findById_Success() {
        // Given
        var user = new User();
        user.setId(1L);
        user.setName("Charles");
        user.setEmail("cgillian@ymail.com");

        // When
        when(repository.findById(1L)).thenReturn(Optional.of(user));

        // Then
        var foundUser = service.findById(1L);
        assertThat(foundUser.getName()).isNotNull();
        assertThat(foundUser.getEmail()).isNotNull();
    }

    @Test
    public void findById_Failed() {
        Exception e = assertThrows(UserIdNotFoundException.class, () -> service.findById(1L));

        var want = "user with id=1 not found";
        var got = e.getMessage();

        assertTrue(got.contains(want));
    }

    @Test
    public void findByEmail_Failed() {
        // Given
        Exception e = assertThrows(UserEmailNotFoundException.class, () -> service.findByEmail("jane@example.com"));

        // Then
        var want = "user email not found";
        var got = e.getMessage();

        assertTrue(got.contains(want));
    }

    @Test
    public void findByName_Failed() {
        // Given
        Exception e = assertThrows(UserNameNotFoundException.class, () ->
                service.findByName("John"));

        // Then
        var want = "user with name=John not found";
        var got = e.getMessage();

        assertTrue(got.contains(want));
    }

    @Test
    public void updateUser_Success() {
        // Given
        var existingUser = new User();
        existingUser.setId(1L);
        existingUser.setName("Charles");
        existingUser.setEmail("cgillian@ymail.com");

        var updatedUser = new User();
        updatedUser.setId(1L);
        updatedUser.setName("Charlie");
        updatedUser.setEmail("charliegillian@ymail.com");

        // When
        when(repository.findById(existingUser.getId())).thenReturn(Optional.of(existingUser));
        when(repository.save(existingUser)).thenReturn(updatedUser);

        // Then
        var result = service.update(updatedUser);

        verify(repository).findById(existingUser.getId());
        verify(repository).save(existingUser);
        assertEquals(updatedUser, result);
    }

    @Test
    public void updateUser_userNotExists_Failed() {
        // Given
        var user = new User();
        user.setId(1L);
        user.setName("Charles");
        user.setEmail("cgillian@ymail.com");

        // When
        when(repository.findById(user.getId())).thenReturn(Optional.empty());

        // Then
        assertThrows(UserIdNotFoundException.class, () -> service.update(user));

        verify(repository).findById(user.getId());
    }

    @Test
    public void updateUser_notValidEmail_Failed() {
        // Given
        var user = new User();
        user.setId(1L);
        user.setName("Charles");
        user.setEmail("cgillianymail.com");

        // When
        when(repository.findById(user.getId())).thenReturn(Optional.of(user));

        // Then
        assertThrows(UserEmailNotValidException.class, () -> service.update(user));
    }

    @Test
    public void updateUser_notValidName_Failed() {
        // Given
        var user = new User();
        user.setId(1L);
        user.setName("");
        user.setEmail("cgillian@ymail.com");

        // When
        when(repository.findById(user.getId())).thenReturn(Optional.of(user));

        // Then
        assertThrows(UserFieldNotValidException.class, () -> service.update(user));
    }

    @Test
    public void userDelete_Success() {
        // Given
        long userId = 1L;
        var user = new User();
        user.setId(userId);
        user.setName("Charles");
        user.setEmail("cgillian@ymail.com");

        var p1 = new Project();
        p1.setId(1L);
        p1.setName("Project Lambda");
        p1.setUsers(List.of(user));

        var p2 = new Project();
        p2.setId(1L);
        p2.setName("Project Equinox");
        p2.setUsers(List.of(user));

        user.setProjects(new HashSet<>());

        // When
        when(repository.findById(userId)).thenReturn(Optional.of(user));

        // Then
        service.delete(userId);
        verify(repository).findById(userId);
        verify(repository).deleteById(userId);
        assertTrue(user.getProjects().isEmpty());
    }
}
