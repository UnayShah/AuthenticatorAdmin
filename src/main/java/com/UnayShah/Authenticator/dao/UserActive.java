package com.UnayShah.Authenticator.dao;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "UserActive")
public class UserActive {
	@Id
	String sessionId;
	String username;

	public UserActive(String username) {
		this.sessionId = UUID.randomUUID().toString();
		this.username = username;
	}

	public String getSessionId() {
		return sessionId;
	}
}