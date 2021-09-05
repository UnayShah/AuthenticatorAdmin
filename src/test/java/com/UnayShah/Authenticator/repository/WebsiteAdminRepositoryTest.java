package com.UnayShah.Authenticator.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import com.UnayShah.Authenticator.dao.Website;
import com.UnayShah.Authenticator.dao.WebsiteAdmin;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class WebsiteAdminRepositoryTest {
    @Autowired
    WebsiteAdminRepository websiteAdminRepository;

    private static WebsiteAdmin websiteAdmin;
    private static String username;
    private static Website website1;
    private static Website website2;

    @BeforeAll
    public static void initialize() {
        username = UUID.randomUUID().toString();
        website1 = new Website(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        website2 = new Website(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        websiteAdmin = new WebsiteAdmin(username);
        websiteAdmin.addWebsite(website1);
    }

    @Order(1)
    @Test
    public void saveTest() {
        assertEquals(websiteAdminRepository.save(websiteAdmin), websiteAdmin);
    }

    @Order(2)
    @Test
    public void findTest() {
    	assertTrue(websiteAdminRepository.findById(username).isPresent());
        Assertions.assertThat(websiteAdmin).usingRecursiveComparison().isEqualTo(websiteAdminRepository.findById(username).get());
        websiteAdmin.addWebsite(website2);
    	Assertions.assertThat(websiteAdmin).usingRecursiveComparison().isNotEqualTo(websiteAdminRepository.findById(username).get());
    }

    @Order(3)
    @Test
    public void updateTest() {
        websiteAdminRepository.save(websiteAdmin);
        Assertions.assertThat(websiteAdmin).usingRecursiveComparison()
                .isEqualTo(websiteAdminRepository.findById(username).get());
        websiteAdmin.removeWebsite(website1);
        Assertions.assertThat(websiteAdmin).usingRecursiveComparison()
                .isNotEqualTo(websiteAdminRepository.findById(username).get());
        websiteAdminRepository.save(websiteAdmin);
        Assertions.assertThat(websiteAdmin).usingRecursiveComparison()
                .isEqualTo(websiteAdminRepository.findById(username).get());
    }

    @Order(4)
    @Test
    public void deleteTest() {
        websiteAdminRepository.deleteById(username);
        assertFalse(websiteAdminRepository.findById(username).isPresent());
    }
}