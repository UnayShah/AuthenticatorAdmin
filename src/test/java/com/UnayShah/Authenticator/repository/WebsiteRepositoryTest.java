package com.UnayShah.Authenticator.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import com.UnayShah.Authenticator.dao.Website;

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
public class WebsiteRepositoryTest {
    @Autowired
    WebsiteRepository websiteRepository;

    private static Website website;
    private static String websiteDisplayName;
    private static String websiteRedirectURL;

    @BeforeAll
    public static void initialize() {
        websiteDisplayName = UUID.randomUUID().toString();
        websiteRedirectURL = UUID.randomUUID().toString();
        website = new Website(websiteDisplayName, websiteRedirectURL);
    }

    @Test
    @Order(1)
    public void saveTest() {
        assertEquals(website, websiteRepository.save(website));
    }

    @Test
    @Order(2)
    public void findTest() {
        assertTrue(websiteRepository.findById(website.getWebsiteId()).isPresent());
        assertTrue(new ReflectionEquals(website, new String[0])
                .matches(websiteRepository.findById(website.getWebsiteId()).get()));
        // If this is true, then website id with this display name is present
        assertFalse(websiteRepository.findById(website.getWebsiteDisplayName()).isPresent());
    }

    @Test
    @Order(3)
    public void removeTest() {
        assertTrue(websiteRepository.findById(website.getWebsiteId()).isPresent());
        websiteRepository.deleteById(website.getWebsiteId());
        assertFalse(websiteRepository.findById(website.getWebsiteId()).isPresent());
    }
}
