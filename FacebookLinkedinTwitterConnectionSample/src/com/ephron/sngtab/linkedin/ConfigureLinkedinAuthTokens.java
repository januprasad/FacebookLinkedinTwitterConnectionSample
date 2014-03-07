package com.ephron.sngtab.linkedin;

import com.google.code.linkedinapi.client.LinkedInApiClient;
import com.google.code.linkedinapi.client.LinkedInApiClientFactory;
import com.google.code.linkedinapi.client.oauth.LinkedInAccessToken;
import com.google.code.linkedinapi.client.oauth.LinkedInOAuthService;
import com.google.code.linkedinapi.client.oauth.LinkedInOAuthServiceFactory;
import com.google.code.linkedinapi.client.oauth.LinkedInRequestToken;

public class ConfigureLinkedinAuthTokens {
	public static final String CONSUMER_KEY = "enter your key";
	public static final String CONSUMER_SECRET = "enter your key";

	public static final String APP_NAME = "Linky";
	public static final String OAUTH_CALLBACK_SCHEME = "x-oauthflow-linkedin";
	public static final String OAUTH_CALLBACK_HOST = "litestcalback";
	public static final String OAUTH_CALLBACK_URL = OAUTH_CALLBACK_SCHEME
			+ "://" + OAUTH_CALLBACK_HOST;
	private LinkedInOAuthService oAuthService;
	private LinkedInApiClientFactory factory;
	private LinkedInRequestToken liToken;
	private LinkedInApiClient client;
	private LinkedInAccessToken accessToken=null;

	public ConfigureLinkedinAuthTokens() {
		// TODO Auto-generated constructor stub
		oAuthService = LinkedInOAuthServiceFactory.getInstance()
				.createLinkedInOAuthService(CONSUMER_KEY, CONSUMER_SECRET);
		factory = LinkedInApiClientFactory.newInstance(CONSUMER_KEY,
				CONSUMER_SECRET);

	}

	public LinkedInOAuthService getoAuthService() {
		return oAuthService;
	}

	public void setoAuthService(LinkedInOAuthService oAuthService) {
		this.oAuthService = oAuthService;
	}

	public LinkedInApiClientFactory getFactory() {
		return factory;
	}

	public void setFactory(LinkedInApiClientFactory factory) {
		this.factory = factory;
	}

	public LinkedInRequestToken getLiToken() {
		return liToken;
	}

	public void setLiToken(LinkedInRequestToken liToken) {
		this.liToken = liToken;
	}

	public LinkedInApiClient getClient() {
		return client;
	}

	public void setClient(LinkedInApiClient client) {
		this.client = client;
	}

	public LinkedInAccessToken getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(LinkedInAccessToken accessToken) {
		
		this.accessToken = accessToken;
	}


}
