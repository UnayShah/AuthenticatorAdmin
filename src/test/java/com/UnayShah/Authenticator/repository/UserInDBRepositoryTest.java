package com.UnayShah.Authenticator.repository;

import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.UnayShah.Authenticator.dao.UserInDB;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class UserInDBRepositoryTest {

	@Autowired
	UserInDBRepository userInDBRepository;

	static UserInDB userInDB;
	private static String username;
	private static String password;
	private static String websiteId1;
	private static String websiteId2;

	@BeforeAll
	public static void initialize() {
		username = UUID.randomUUID().toString();
		password = UUID.randomUUID().toString();
		websiteId1 = UUID.randomUUID().toString();
		websiteId2 = UUID.randomUUID().toString();
		userInDB = new UserInDB(username, password);
		userInDB.addWebsiteId(websiteId1);
		assertEquals(username, userInDB.getUsername());
		assertFalse(userInDB.addWebsiteId(websiteId1));
	}

	@Test
	@Order(1)
	public void saveTest() {
		assertEquals(userInDB, userInDBRepository.save(userInDB));
	}

	@Test
	@Order(2)
	public void findTest() {
		assertTrue(new ReflectionEquals(userInDB, new String[0]).matches(userInDBRepository.findById(username).get()));
	}

	@Test
	@Order(3)
	public void findByCredentialsTest() {
		assertTrue(userInDBRepository.findByCredentials(username, password).isPresent());
		assertTrue(new ReflectionEquals(userInDB, new String[0])
				.matches(userInDBRepository.findByCredentials(username, password).get()));
		assertFalse(userInDBRepository.findByCredentials(username, websiteId1).isPresent());
	}

	@Test
	@Order(4)
	public void findWebsiteOptionalTest() {
		assertTrue(userInDBRepository.findWebsiteOptional(username, password, websiteId1).isPresent());
		assertFalse(userInDBRepository.findWebsiteOptional(username, password, websiteId2).isPresent());
		assertTrue(new ReflectionEquals(userInDB, new String[0])
				.matches(userInDBRepository.findWebsiteOptional(username, password, websiteId1).get()));
	}

	@Test
	@Order(5)
	public void removeTest() {
		assertTrue(userInDBRepository.findById(username).isPresent());
		userInDBRepository.deleteById(username);
		assertTrue(userInDBRepository.findById(username).isEmpty());
	}
}
