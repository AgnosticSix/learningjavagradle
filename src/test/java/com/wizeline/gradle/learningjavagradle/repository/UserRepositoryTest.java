package com.wizeline.gradle.learningjavagradle.repository;

import com.wizeline.gradle.learningjavagradle.model.UserDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

import static com.wizeline.gradle.learningjavagradle.Datos.Datos.USER_001;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryTest.class);

    @Mock
    MongoTemplate template;

    @InjectMocks
    UserRepositoryImpl userRepository;

    @Test
    public void createUserTest() {
        LOGGER.info("createUser Testing...");

        UserDTO userDTO = new UserDTO(USER_001.getUser(), USER_001.getPassword());

        when(template.save(any())).thenReturn(userDTO);

        assertNotNull(userRepository.createUser(USER_001.getUser(), USER_001.getPassword()));
    }
}
