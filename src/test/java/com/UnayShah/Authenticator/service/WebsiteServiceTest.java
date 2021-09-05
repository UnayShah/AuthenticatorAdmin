package com.UnayShah.Authenticator.service;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import com.UnayShah.Authenticator.dao.Website;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class WebsiteServiceTest {

    @Autowired
    WebsiteService websiteService;

    @Mock
    Website website;

    private static String websiteId;
    private static String websiteDisplayName1;
    private static String websiteDisplayName2;
    private static String websiteRedirectURL1;
    private static String websiteRedirectURL2;

    @BeforeAll
    public static void initialize() {
        websiteDisplayName1 = UUID.randomUUID().toString();
        websiteDisplayName2 = UUID.randomUUID().toString();
        websiteRedirectURL1 = UUID.randomUUID().toString();
        websiteRedirectURL2 = UUID.randomUUID().toString();
    }

    @Test
    @Order(1)
    public void addWebsiteTest() {
        website = websiteService.addWebsite(websiteDisplayName1, websiteRedirectURL1);
        assertNotNull(website.getWebsiteId());
        websiteId = website.getWebsiteId();
        assertEquals(website.getWebsiteDisplayName(), websiteDisplayName1);
        assertNotEquals(website.getWebsiteDisplayName(), websiteDisplayName2);
        assertEquals(website.getWebsiteRedirectURL(), websiteRedirectURL1);
        assertNotEquals(website.getWebsiteRedirectURL(), websiteRedirectURL2);
    }

    @Test
    @Order(2)
    public void findWebsiteTest() {
        website = websiteService.findWebsite(websiteId);
        assertNotNull(website);
        assertEquals(website.getWebsiteDisplayName(), websiteDisplayName1);
        assertNotEquals(website.getWebsiteDisplayName(), websiteDisplayName2);
        assertEquals(website.getWebsiteRedirectURL(), websiteRedirectURL1);
        assertNotEquals(website.getWebsiteRedirectURL(), websiteRedirectURL2);
    }

    @Test
    @Order(3)
    public void editWebsiteDisplayNameTest() {
        assertTrue(websiteService.editWebsiteDisplayName(websiteId, websiteDisplayName1, websiteDisplayName2));
        assertEquals(websiteService.findWebsite(websiteId).getWebsiteDisplayName(), websiteDisplayName2);
        assertNotEquals(websiteService.findWebsite(websiteId).getWebsiteDisplayName(), websiteDisplayName1);
        assertEquals(websiteService.findWebsite(websiteId).getWebsiteRedirectURL(), websiteRedirectURL1);
        assertNotEquals(websiteService.findWebsite(websiteId).getWebsiteRedirectURL(), websiteRedirectURL2);
    }

    @Test
    @Order(4)
    public void editWebsiteDisplayNameFailTest() {
        assertFalse(websiteService.editWebsiteDisplayName(websiteId, websiteDisplayName1, websiteDisplayName2));
        assertEquals(websiteService.findWebsite(websiteId).getWebsiteDisplayName(), websiteDisplayName2);
        assertNotEquals(websiteService.findWebsite(websiteId).getWebsiteDisplayName(), websiteDisplayName1);
        assertEquals(websiteService.findWebsite(websiteId).getWebsiteRedirectURL(), websiteRedirectURL1);
        assertNotEquals(websiteService.findWebsite(websiteId).getWebsiteRedirectURL(), websiteRedirectURL2);
    }

    @Test
    @Order(5)
    public void editWebsiteRedirectURLTest() {
        assertTrue(websiteService.editWebsiteRedirectURL(websiteId, websiteRedirectURL1, websiteRedirectURL2));
        assertEquals(websiteService.findWebsite(websiteId).getWebsiteDisplayName(), websiteDisplayName2);
        assertNotEquals(websiteService.findWebsite(websiteId).getWebsiteDisplayName(), websiteDisplayName1);
        assertEquals(websiteService.findWebsite(websiteId).getWebsiteRedirectURL(), websiteRedirectURL2);
        assertNotEquals(websiteService.findWebsite(websiteId).getWebsiteRedirectURL(), websiteRedirectURL1);
    }

    @Test
    @Order(5)
    public void editWebsiteRedirectURLFailTest() {
        assertFalse(websiteService.editWebsiteRedirectURL(websiteId, websiteRedirectURL1, websiteRedirectURL2));
        assertEquals(websiteService.findWebsite(websiteId).getWebsiteDisplayName(), websiteDisplayName2);
        assertNotEquals(websiteService.findWebsite(websiteId).getWebsiteDisplayName(), websiteDisplayName1);
        assertEquals(websiteService.findWebsite(websiteId).getWebsiteRedirectURL(), websiteRedirectURL2);
        assertNotEquals(websiteService.findWebsite(websiteId).getWebsiteRedirectURL(), websiteRedirectURL1);
    }

    @Test
    @Order(6)
    public void removeWebsiteTest() {
        assertFalse(websiteService.removeWebsite(websiteDisplayName1, websiteDisplayName2, websiteRedirectURL2));
        assertFalse(websiteService.removeWebsite(websiteId, websiteDisplayName1, websiteRedirectURL2));
        assertFalse(websiteService.removeWebsite(websiteId, websiteDisplayName2, websiteRedirectURL1));
        assertTrue(websiteService.removeWebsite(websiteId, websiteDisplayName2, websiteRedirectURL2));
        assertFalse(websiteService.removeWebsite(websiteId, websiteDisplayName2, websiteRedirectURL2));
    }
}
