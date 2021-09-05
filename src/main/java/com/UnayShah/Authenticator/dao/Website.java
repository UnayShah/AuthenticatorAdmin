package com.UnayShah.Authenticator.dao;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Website")
public class Website {
	@Id
	String websiteId;
	String websiteDisplayName;
	String websiteRedirectURL;

	public Website(String websiteDisplayName, String websiteRedirectURL) {
		this.websiteId = UUID.randomUUID().toString();
		this.websiteDisplayName = websiteDisplayName;
		this.websiteRedirectURL = websiteRedirectURL;
	}

	public String getWebsiteId() {
		return websiteId;
	}

	public String getWebsiteDisplayName() {
		return websiteDisplayName;
	}

	public void setWebsiteDisplayName(String websiteDisplayName) {
		this.websiteDisplayName = websiteDisplayName;
	}

	public String getWebsiteRedirectURL() {
		return websiteRedirectURL;
	}

	public void setWebsiteRedirectURL(String websiteRedirectURL) {
		this.websiteRedirectURL = websiteRedirectURL;
	}

	@Override
	public String toString() {
		return "Website [websiteDisplayName=" + websiteDisplayName + ", websiteId=" + websiteId
				+ ", websiteRedirectURL=" + websiteRedirectURL + "]";
	}

}
