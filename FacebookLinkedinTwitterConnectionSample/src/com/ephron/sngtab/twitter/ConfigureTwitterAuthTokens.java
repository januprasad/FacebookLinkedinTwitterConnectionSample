package com.ephron.sngtab.twitter;

import com.ephron.sngtab.base.JenuineActivity;

import twitter4j.Twitter;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class ConfigureTwitterAuthTokens {
	static String TWITTER_CONSUMER_KEY = "enter your key";
	static String TWITTER_CONSUMER_SECRET = "enter your key";

	// Preference Constants
	static String PREFERENCE_NAME = "twitter_oauth";
	static final String PREF_KEY_OAUTH_TOKEN = "oauth_token";
	static final String PREF_KEY_OAUTH_SECRET = "oauth_token_secret";
	static final String PREF_KEY_TWITTER_LOGIN = "isTwitterLogedIn";

	public static final String TWITTER_CALLBACK_URL = "oauth://t4jsample";

	// Twitter oauth urls
	static final String URL_TWITTER_AUTH = "auth_url";
	public static final String URL_TWITTER_OAUTH_VERIFIER = "oauth_verifier";
	static final String URL_TWITTER_OAUTH_TOKEN = "oauth_token";
	private  Twitter twitter;
	private  RequestToken requestToken;
	private AccessToken accessToken;

	public AccessToken getAccessToken() {
		return accessToken;
	}



	public void setAccessToken(AccessToken accessToken) {
		this.accessToken = accessToken;
	}



	// Shared Preferences
	private static SharedPreferences mSharedPreferences;
	private JenuineActivity activity;

	

	public Twitter getTwitter() {
		return twitter;
	}



	public void setTwitter(Twitter twitter) {
		this.twitter = twitter;
	}



	public RequestToken getRequestToken() {
		return requestToken;
	}



	public void setRequestToken(RequestToken requestToken) {
		this.requestToken = requestToken;
	}



	// Internet Connection detector
	public ConfigureTwitterAuthTokens(JenuineActivity activity) {
		this.activity = activity;
		
		if (TWITTER_CONSUMER_KEY.trim().length() == 0
				|| TWITTER_CONSUMER_SECRET.trim().length() == 0) {
			// Internet Connection is not present
			activity.getMessage().showAlertDialog(activity, "Twitter oAuth tokens",
					"Please set your twitter oauth tokens first!", false);
			// stop executing code by return
			return;
		}
		
		// TODO Auto-generated constructor stub
	}

}
