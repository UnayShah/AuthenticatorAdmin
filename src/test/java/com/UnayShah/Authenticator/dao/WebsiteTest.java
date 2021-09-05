package com.UnayShah.Authenticator.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(OrderAnnotation.class)
public class WebsiteTest {

    @Mock
    Website website;

    private static String websiteDisplayName1;
    private static String websiteRedirectURL1;

    @BeforeAll
    public static void initialize() {
        websiteDisplayName1 = UUID.randomUUID().toString();
        websiteRedirectURL1 = UUID.randomUUID().toString();
    }

    @Test
    @Order(1)
    public void websiteIdTest() {
        assertNotNull(new Website(websiteDisplayName1, websiteRedirectURL1).getWebsiteId());
    }

    @Test
    @Order(2)
    public void websiteDisplayNameTest() {
        website = new Website(websiteDisplayName1, websiteRedirectURL1);
        assertEquals(website.getWebsiteDisplayName(), websiteDisplayName1);
        assertNotEquals(website.getWebsiteDisplayName(), websiteRedirectURL1);
        website.setWebsiteDisplayName(websiteRedirectURL1);
        assertNotEquals(website.getWebsiteDisplayName(), websiteDisplayName1);
        assertEquals(website.getWebsiteDisplayName(), websiteRedirectURL1);
    }

    @Test
    @Order(3)
    public void websiteRedirectURLTest() {
        website = new Website(websiteDisplayName1, websiteRedirectURL1);
        assertEquals(website.getWebsiteRedirectURL(), websiteRedirectURL1);
        assertNotEquals(website.getWebsiteRedirectURL(), websiteDisplayName1);
        website.setWebsiteDisplayName(websiteDisplayName1);
        assertNotEquals(website.getWebsiteDisplayName(), websiteRedirectURL1);
        assertEquals(website.getWebsiteDisplayName(), websiteDisplayName1);
    }
}
