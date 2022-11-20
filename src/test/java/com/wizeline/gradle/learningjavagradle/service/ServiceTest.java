package com.wizeline.gradle.learningjavagradle.service;

import com.wizeline.gradle.learningjavagradle.model.BankAccountDTO;
import com.wizeline.gradle.learningjavagradle.model.UserDTO;
import com.wizeline.gradle.learningjavagradle.repository.BankingAccountRepository;
import com.wizeline.gradle.learningjavagradle.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ServiceTest {
    @Mock
    UserRepository userRepository;

    @Mock
    BankingAccountRepository bankingAccountRepository;

    UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userRepository, bankingAccountRepository);
    }
}
