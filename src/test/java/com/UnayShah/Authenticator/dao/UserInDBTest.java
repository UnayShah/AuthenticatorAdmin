package com.UnayShah.Authenticator.dao;

import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(OrderAnnotation.class)
public class UserInDBTest {
	@Mock
	UserInDB userInDB;

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
	}

	@Test
	@Order(1)
	public void constructor1Test() {
		userInDB = new UserInDB(username, password);
		assertEquals(username, userInDB.getUsername());
	}
	
	@Test
	@Order(2)
	public void constructorAddSingleWebsiteIdTest() {
		userInDB = new UserInDB(username, password);
		assertTrue(userInDB.addWebsiteId(websiteId1));
		assertFalse(userInDB.addWebsiteId(websiteId1));
	}
	
	@Test
	@Order(3)
	public void removeWebsiteIdTest() {
		userInDB = new UserInDB(username, password);
		assertTrue(userInDB.addWebsiteId(websiteId1));
		assertTrue(userInDB.removeWebsiteId(websiteId1));
		assertFalse(userInDB.removeWebsiteId(websiteId1));
	}
	
	@Test
	@Order(4)
	public void constructorAddMultipleWebsiteIdTest() {
		userInDB = new UserInDB(username, password);
		assertTrue(userInDB.addWebsiteId(websiteId1, websiteId2));
		assertFalse(userInDB.addWebsiteId(websiteId2));
		assertFalse(userInDB.addWebsiteId(websiteId1, websiteId2));
	}
}
