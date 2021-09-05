package com.UnayShah.Authenticator.service;

import com.UnayShah.Authenticator.dao.WebsiteAdmin;
import com.UnayShah.Authenticator.repository.WebsiteAdminRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebsiteAdminService {
    @Autowired
    WebsiteAdminRepository websiteAdminRepository;

    public WebsiteAdmin register(String username, String sessionId) {
        if (websiteAdminRepository.findById(username).isEmpty())
            return websiteAdminRepository.save(new WebsiteAdmin(username));
        return null;
    }
}
