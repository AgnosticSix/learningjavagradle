package com.wizeline.gradle.learningjavagradle.controller;

import com.wizeline.gradle.learningjavagradle.model.ResponseDTO;
import com.wizeline.gradle.learningjavagradle.model.UserDTO;
import com.wizeline.gradle.learningjavagradle.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


public class UserControllerTest {
    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    @Mock
    UserDTO userDTO;

    @Mock
    ResponseDTO responseDTO;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUserTest() {
        when(userService.createUser(userDTO.getUser(), userDTO.getPassword())).thenReturn(responseDTO);

        assertNotNull(userController.createUser(userDTO));
    }

    @Test
    void loginTest() {
        when(userService.login("user", "password")).thenReturn(responseDTO);
        assertNotNull(userController.loginUser("user", "password"));
    }





}
