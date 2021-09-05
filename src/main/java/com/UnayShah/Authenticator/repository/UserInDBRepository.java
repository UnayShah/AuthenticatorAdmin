package com.UnayShah.Authenticator.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.UnayShah.Authenticator.dao.UserInDB;

@Repository
public interface UserInDBRepository extends MongoRepository<UserInDB, String> {

	/**
	 * find if the username and password combination exist in the database
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@Query(value = "{$and: [{_id: ?0}, {password: ?1}]}")
	public Optional<UserInDB> findByCredentials(String username, String password);

	/**
	 * find if a username and password exist and the provided website is associated
	 * with the combination
	 * 
	 * @param username
	 * @param password
	 * @param websiteId
	 * @return
	 */
	@Query(value = "{$and: [{_id: ?0}, {password: ?1}, {websiteId: {$in: [?2]}}]}")
	public Optional<UserInDB> findWebsiteOptional(String username, String password, String websiteId);
}