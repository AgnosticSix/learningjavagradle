package com.wizeline.gradle.learningjavagradle.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.wizeline.gradle.learningjavagradle.model.RandomPassword;
import com.wizeline.gradle.learningjavagradle.model.ResponseDTO;
import com.wizeline.gradle.learningjavagradle.model.UserDTO;
import com.wizeline.gradle.learningjavagradle.repository.UserRepositoryImpl;
import com.wizeline.gradle.learningjavagradle.singleton.RestTemplateConfig;
import com.wizeline.gradle.learningjavagradle.utils.EncryptorRSA;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	@Mock
	private MongoTemplate template;
	
	@InjectMocks
	private UserRepositoryImpl userRepositoryImpl;

	@Mock
	private EncryptorRSA encryptorRSA;
	
	@InjectMocks
	private UserServiceImpl userServiceImpl;
	
	@Mock
	UserDTO userDTO;
	
	private static final String USER = "mateo";
	
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void createUser() 
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		RandomPassword password = RestTemplateConfig.getInstance().getRandomPassword();
		assertNotNull(password);
		
		String passwordEncriptada = encryptorRSA.encrypt(password.toString());

		assertEquals(encryptorRSA.decrypt(passwordEncriptada), password.toString());
		
		userDTO.setUser(USER);
		userDTO.setPassword(passwordEncriptada);
				
		when(template.save(userDTO)).thenReturn(userDTO);
		ResponseDTO response = userServiceImpl.createUser(USER, passwordEncriptada);
		
		assertAll(
				() -> assertNotNull(response),
				() -> assertEquals(response.getCode(), "200")
				);
	}

	@Test
	public void createUserError() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		RandomPassword password = RestTemplateConfig.getInstance().getRandomPassword();
		assertNotNull(password);
		
		String passwordEncriptada = encryptorRSA.encrypt(password.toString());
		
		assertEquals(encryptorRSA.decrypt(passwordEncriptada), password.toString());
		
		userDTO.setUser(USER);
		userDTO.setPassword(passwordEncriptada);
				
		when(template.save(userDTO)).thenReturn(null);
		ResponseDTO response = userServiceImpl.createUser(USER, passwordEncriptada);
		
		assertAll(
				() -> assertNotNull(response),
				() -> assertEquals(response.getCode(), "400")
				);
	}
}
