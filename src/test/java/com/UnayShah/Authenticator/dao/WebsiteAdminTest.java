package com.UnayShah.Authenticator.dao;

import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(OrderAnnotation.class)
public class WebsiteAdminTest {

    @Mock
    WebsiteAdmin websiteAdmin;
    static String username;
    @Mock
    Website website1;
    @Mock
    Website website2;
    @Spy
    Set<Website> websiteList = new HashSet<>();

    @BeforeAll
    public static void initialize() {
        username = UUID.randomUUID().toString();
    }

    @AfterEach
    public void terminate() {
        Mockito.verifyNoMoreInteractions(websiteAdmin);
    }

    @Order(1)
    @Test
    public void constructorTest() {
        websiteAdmin.getUsername();
        Mockito.verify(websiteAdmin).getUsername();
    }

    @Order(2)
    @Test
    public void usernameTest() {
        websiteAdmin.getUsername();
        Mockito.verify(websiteAdmin).getUsername();
    }

    @Order(3)
    @Test
    public void addWebsitesTest() {
        websiteAdmin.addWebsite(website1);
        Mockito.verify(websiteAdmin).addWebsite(website1);
        websiteAdmin.addWebsite(website2);
        Mockito.verify(websiteAdmin).addWebsite(website2);
    }

    @Order(4)
    @Test
    public void websiteListTest() {
        websiteAdmin.addWebsite(website1);
        Mockito.verify(websiteAdmin).addWebsite(website1);
        websiteAdmin.addWebsite(website2);
        Mockito.verify(websiteAdmin).addWebsite(website2);
        websiteList.add(website1);
        websiteList.add(website2);
        Mockito.when(websiteAdmin.getListWebsites()).thenReturn(websiteList);
        assertEquals(websiteAdmin.getListWebsites(), websiteList);
    }

    @Order(5)
    @Test
    public void removeWebsitesTest() {
        websiteAdmin.addWebsite(website1);
        Mockito.verify(websiteAdmin).addWebsite(website1);
        websiteAdmin.addWebsite(website2);
        Mockito.verify(websiteAdmin).addWebsite(website2);
        websiteAdmin.removeWebsite(website1);
        Mockito.verify(websiteAdmin).removeWebsite(website1);
        websiteAdmin.removeWebsite(website2);
        Mockito.verify(websiteAdmin).removeWebsite(website2);
    }

}
