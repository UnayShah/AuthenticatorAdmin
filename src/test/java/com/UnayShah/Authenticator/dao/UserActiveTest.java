package com.UnayShah.Authenticator.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(OrderAnnotation.class)
public class UserActiveTest {
	@Mock
	UserActive userActive;
	String username = UUID.randomUUID().toString();

	@Test
	@Order(1)
	public void constructorTest() {
		userActive = new UserActive(username);
		assertNotNull(userActive.getSessionId());
	}
}
