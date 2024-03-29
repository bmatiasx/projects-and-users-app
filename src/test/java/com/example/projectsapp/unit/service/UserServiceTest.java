package com.example.projectsapp.unit.service;

import com.example.projectsapp.exception.UserEmailExistsException;
import com.example.projectsapp.exception.UserEmailNotValidException;
import com.example.projectsapp.exception.UserEmailNotFoundException;
import com.example.projectsapp.exception.UserFieldNotValidException;
import com.example.projectsapp.exception.UserIdNotFoundException;
import com.example.projectsapp.exception.UserNameNotFoundException;
import com.example.projectsapp.exception.UsersNotCreatedException;
import com.example.projectsapp.model.User;
import com.example.projectsapp.repository.UserRepository;
import com.example.projectsapp.service.UserService;
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
        long userId = 1L;
        String name = "Charles";
        String email = "cgillian@ymail.com";
        var user = User.builder().id(userId).name(name).email(email).build();

        // When
        when(repository.save(any(User.class))).thenReturn(user);

        // Then
        User savedUser = service.create(user);
        assertThat(savedUser.getName()).isNotNull();
    }

    @Test
    public void saveUser_notValidEmail_Failed() {
        long userId = 1L;
        String name = "Charles";
        String email = "cgillianymail.com";
        var user = User.builder().id(userId).name(name).email(email).build();

        Exception e = assertThrows(UserEmailNotValidException.class, () -> service.create(user));

        var want = "email format is not valid";
        var got = e.getMessage();

        assertTrue(got.contains(want));
    }

    @Test
    public void saveUser_userExists_Failed() {
        // Given
        long userId = 1L;
        String name = "Charles";
        String email = "cgillian@ymail.com";
        var user = User.builder().id(userId).name(name).email(email).build();

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
        long userId = 1L;
        String name = "Charles";
        String email = "";
        var user = User.builder().id(userId).name(name).email(email).build();

        // Then
        Exception e = assertThrows(UserEmailNotValidException.class, () -> service.create(user));

        var want = "email format is not valid";
        var got = e.getMessage();

        assertTrue(got.contains(want));
    }

    @Test
    public void saveUser_blankName_Failed() {
        // Given
        long userId = 1L;
        String name = "";
        String email = "cgillian@ymail.com";
        var user = User.builder().id(userId).name(name).email(email).build();

        // Then
        Exception e = assertThrows(UserFieldNotValidException.class, () -> service.create(user));

        var want = "field name is not valid";
        var got = e.getMessage();

        assertTrue(got.contains(want));
    }

    @Test
    public void findAll_Success() {
        // Given
        long userId1 = 1L;
        String name1 = "Charles";
        String email1 = "cgillian@ymail.com";
        var user1 = User.builder().id(userId1).name(name1).email(email1).build();

        long userId2 = 1L;
        String name2 = "Hanna";
        String email2 = "hbeckam@gmail.com";
        var user2 = User.builder().id(userId2).name(name2).email(email2).build();

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
        long userId = 1L;
        String name = "Charles";
        String email = "cgillian@ymail.com";
        var user = User.builder().id(userId).name(name).email(email).build();

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
        long userId = 1L;
        String name = "Charles";
        String email = "cgillian@ymail.com";
        var existingUser = User.builder().id(userId).name(name).email(email).build();

        String updatedEmail = "charliegillian@ymail.com";
        var updatedUser = User.builder().id(userId).name(name).email(updatedEmail).build();

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
        long userId = 1L;
        String name = "Charles";
        String email = "cgillian@ymail.com";
        var user = User.builder().id(userId).name(name).email(email).build();

        // When
        when(repository.findById(user.getId())).thenReturn(Optional.empty());

        // Then
        assertThrows(UserIdNotFoundException.class, () -> service.update(user));

        verify(repository).findById(user.getId());
    }

    @Test
    public void updateUser_notValidEmail_Failed() {
        // Given
        long userId = 1L;
        String name = "Charles";
        String email = "cgillianymail.com";
        var user = User.builder().id(userId).name(name).email(email).build();

        // When
        when(repository.findById(user.getId())).thenReturn(Optional.of(user));

        // Then
        assertThrows(UserEmailNotValidException.class, () -> service.update(user));
    }

    @Test
    public void updateUser_notValidName_Failed() {
        // Given
        long userId = 1L;
        String name = "";
        String email = "cgillian@ymail.com";
        var user = User.builder().id(userId).name(name).email(email).build();

        // When
        when(repository.findById(user.getId())).thenReturn(Optional.of(user));

        // Then
        assertThrows(UserFieldNotValidException.class, () -> service.update(user));
    }

    @Test
    public void userDelete_Success() {
        // Given
        long userId = 1L;
        String name = "Charles";
        String email = "cgillian@ymail.com";
        var user = User.builder().id(userId).name(name).email(email).build();

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
