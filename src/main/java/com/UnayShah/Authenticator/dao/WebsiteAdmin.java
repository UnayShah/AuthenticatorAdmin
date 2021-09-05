package com.UnayShah.Authenticator.dao;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "WebsiteAdmin")
public class WebsiteAdmin {
	@Id
	private String username;
	private Set<Website> listWebsites;

	public WebsiteAdmin(String username) {
		this.username = username;
		this.listWebsites = new LinkedHashSet<>();
	}

	public String getUsername() {
		return username;
	}

	public Set<Website> getListWebsites() {
		return listWebsites;
	}

	public Boolean addWebsite(Website website) {
		return this.listWebsites.add(website);
	}

	public Boolean removeWebsite(Website website) {
		return this.listWebsites.remove(website);
	}
}
