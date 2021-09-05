package com.UnayShah.Authenticator.dao;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import com.UnayShah.Authenticator.core.CommonConstants;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "UserInDB")
public class UserInDB {
	@Id
	String username;
	String password;
	Set<String> websiteId;

	public UserInDB(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		if (websiteId == null)
			this.websiteId = new TreeSet<>();
		this.websiteId.add(CommonConstants.AUTHENTICATOR_WEBSITE_ID);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean addWebsiteId(String... websiteId) {
		return this.websiteId.addAll(Arrays.asList(websiteId));
	}

	public boolean removeWebsiteId(String websiteId) {
		return this.websiteId.remove(websiteId);
	}
}
