package com.UnayShah.Authenticator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UnayShah.Authenticator.dao.UserInDB;
import com.UnayShah.Authenticator.repository.UserInDBRepository;

@Service
public class UserInDBService {
	@Autowired
	private UserInDBRepository userInDBRepository;

	/**
	 * register a new user to the authenticator
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public UserInDB newUser(String username, String password) {
		if (!userInDBRepository.existsById(username))
			return userInDBRepository.save(new UserInDB(username, password));
		else
			return null;
	}

	/**
	 * handle user login and find if user already exists in database
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public Boolean findUser(String username, String password) {
		return userInDBRepository.findByCredentials(username, password).isPresent();
	}

	/**
	 * check if the user is registered with the website
	 * 
	 * @param username
	 * @param password
	 * @param websiteId
	 * @return
	 */
	public Boolean registeredUser(String username, String password, String websiteId) {
		return findUser(username, password)
				&& userInDBRepository.findWebsiteOptional(username, password, websiteId).isPresent();
	}

	/**
	 * if user is not registered with the website, add the website to the user entry
	 * 
	 * @param username
	 * @param password
	 * @param websiteId
	 * @return
	 */
	public Boolean addWebsite(String username, String password, String websiteId) {
		if (findUser(username, password) && !registeredUser(username, password, websiteId)) {
			UserInDB userInDB = userInDBRepository.findByCredentials(username, password).get();
			userInDB.addWebsiteId(websiteId);
			userInDBRepository.save(userInDB);
			return true;
		}
		return false;
	}

	/**
	 * remove a website from a user's registered websites list
	 * 
	 * @param username
	 * @param password
	 * @param websiteId
	 * @return
	 */
	public Boolean removeWebsite(String username, String password, String websiteId) {
		if (registeredUser(username, password, websiteId)) {
			UserInDB userInDB = userInDBRepository.findByCredentials(username, password).get();
			userInDB.removeWebsiteId(websiteId);
			userInDBRepository.save(userInDB);
			return true;
		}
		return false;
	}

	/**
	 * change password of a user
	 * 
	 * @param username
	 * @param password
	 * @param newPassword
	 * @return
	 */
	public Boolean editUser(String username, String password, String newPassword) {
		if (findUser(username, password)) {
			UserInDB userInDB = userInDBRepository.findByCredentials(username, password).get();
			userInDB.setPassword(newPassword);
			userInDBRepository.save(userInDB);
			return true;
		}
		return false;
	}

	/**
	 * remove a user entirely from autheticator
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public Boolean removeUser(String username, String password) {
		if (findUser(username, password)) {
			userInDBRepository.deleteById(username);
			return !userInDBRepository.findById(username).isPresent();
		} else
			return false;
	}
}