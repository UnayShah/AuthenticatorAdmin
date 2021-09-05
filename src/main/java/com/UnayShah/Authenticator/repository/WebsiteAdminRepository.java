package com.UnayShah.Authenticator.repository;

import com.UnayShah.Authenticator.dao.WebsiteAdmin;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebsiteAdminRepository extends MongoRepository<WebsiteAdmin, String> {
    
}
