package com.UnayShah.Authenticator.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import com.UnayShah.Authenticator.dao.UserActive;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class UserActiveRepositoryTest {
    @Autowired
    UserActiveRepository userActiveRepository;

    static UserActive userActive;
    private static String sessionId;
    private static String username;

    @BeforeAll
    public static void initialize() {
        username = UUID.randomUUID().toString();
        userActive = new UserActive(username);
        sessionId = userActive.getSessionId();
    }

    @Test
    @Order(1)
    public void saveTest() {
        assertEquals(userActive, userActiveRepository.save(userActive));
    }

    @Test
    @Order(2)
    public void findTest() {
        assertTrue(new ReflectionEquals(userActive, new String[0])
                .matches(userActiveRepository.findById(sessionId).get()));
    }

    @Test
    @Order(3)
    public void findByAllParameters() {
        assertNotNull(userActiveRepository.findByAllParameters(sessionId, username));
        assertTrue(new ReflectionEquals(userActive, new String[0])
                .matches(userActiveRepository.findByAllParameters(sessionId, username).get()));
    }

    @Test
    @Order(4)
    public void removeTest() {
        assertTrue(userActiveRepository.findById(sessionId).isPresent());
        userActiveRepository.deleteById(sessionId);
        assertTrue(userActiveRepository.findById(sessionId).isEmpty());
    }
}
