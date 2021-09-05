package com.UnayShah.Authenticator.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.UnayShah.Authenticator.dao.Website;

@Repository
public interface WebsiteRepository extends MongoRepository<Website, String> {
}