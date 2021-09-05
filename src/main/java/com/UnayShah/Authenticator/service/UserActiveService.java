package com.UnayShah.Authenticator.service;

import com.UnayShah.Authenticator.dao.UserActive;
import com.UnayShah.Authenticator.repository.UserActiveRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserActiveService {

    @Autowired
    UserActiveRepository userActiveRepository;
    @Autowired
    UserInDBService userInDBService;

    /**
     * handle user login
     * 
     * @param username
     * @param password
     * @param websiteId
     * @return
     */
    public UserActive login(String username, String password, String websiteId) {
        if (userInDBService.registeredUser(username, password, websiteId))
            return userActiveRepository.save(new UserActive(username));
        return null;
    }

    /**
     * refresh a user session by changing login time to be implemented in the future
     * 
     * @param sessionId
     * @param username
     * @return
     */
    public UserActive refreshSession(String sessionId, String username) {
        // TO DO: add timestamp field for logged in user and modify it to current time
        if (userActiveRepository.findByAllParameters(sessionId, username).isPresent())
            return userActiveRepository.findByAllParameters(sessionId, username).get();
        return null;
    }

    /**
     * logout user and remove session id
     * 
     * @param sessionId
     * @param username
     * @return
     */
    public Boolean logout(String sessionId, String username) {
        if (userActiveRepository.findByAllParameters(sessionId, username).isPresent()) {
            userActiveRepository.delete(userActiveRepository.findByAllParameters(sessionId, username).get());
            return true;
        }
        return false;
    }
}
