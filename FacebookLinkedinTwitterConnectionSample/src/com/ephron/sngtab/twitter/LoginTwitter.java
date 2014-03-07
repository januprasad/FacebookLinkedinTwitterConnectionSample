package com.ephron.sngtab.twitter;

import com.ephron.sngtab.base.JenuineActivity;
import com.ephron.sngtab.util.UIMessage;

import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class LoginTwitter {

	private ConfigureTwitterAuthTokens cTT;
	private JenuineActivity activity;

	public LoginTwitter() {
		// TODO Auto-generated constructor stub
	}

	public LoginTwitter(ConfigureTwitterAuthTokens cTT, JenuineActivity activity) {
		// TODO Auto-generated constructor stub
		this.cTT = cTT;
		this.activity = activity;
	}

	public void loginToTwitter(UIMessage message) {
		// Check if already logged in
		// if (!isTwitterLoggedInAlready()) {
		Log.e("Login", "in View");
		
		
		
		
		ConfigurationBuilder builder = new ConfigurationBuilder();
		builder.setOAuthConsumerKey(ConfigureTwitterAuthTokens.TWITTER_CONSUMER_KEY);
		builder.setOAuthConsumerSecret(ConfigureTwitterAuthTokens.TWITTER_CONSUMER_SECRET);
		Configuration configuration = builder.build();

		TwitterFactory factory = new TwitterFactory(configuration);
		cTT.setTwitter(factory.getInstance());
		System.out.println(factory);
		System.out.println(cTT.getTwitter());

		try {
			cTT.setRequestToken(cTT.getTwitter().getOAuthRequestToken(
					ConfigureTwitterAuthTokens.TWITTER_CALLBACK_URL));
			activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(cTT
					.getRequestToken().getAuthenticationURL())));
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		// } else {
		//
		// btnLoginTwitter.setVisibility(View.GONE);
		//
		// // Show Update Twitter
		// lblUpdate.setVisibility(View.VISIBLE);
		// txtUpdate.setVisibility(View.VISIBLE);
		// btnUpdateStatus.setVisibility(View.VISIBLE);
		// btnLogoutTwitter.setVisibility(View.VISIBLE);
		//
		// // user already logged into twitter
		// Toast.makeText(getApplicationContext(),
		// "Already Logged into twitter", Toast.LENGTH_LONG).show();
		// }
	}

	public String getAccessTokenTwitter(Uri uri) {

		// oAuth verifier
		Log.e("oAuth verifier", "verify");
		String verifier = uri
				.getQueryParameter(ConfigureTwitterAuthTokens.URL_TWITTER_OAUTH_VERIFIER);

		try {
			// Get the access token
			AccessToken accessToken = cTT.getTwitter().getOAuthAccessToken(
					cTT.getRequestToken(), verifier);

			// Shared Preferences
			// Editor e = mSharedPreferences.edit();
			//
			// // After getting access token, access token secret
			// // store them in application preferences
			// e.putString(PREF_KEY_OAUTH_TOKEN, accessToken.getToken());
			// e.putString(PREF_KEY_OAUTH_SECRET,
			// accessToken.getTokenSecret());
			// // Store login status - true
			// e.putBoolean(PREF_KEY_TWITTER_LOGIN, true);
			// e.commit(); // save changes

			Log.e("Twitter OAuth Token", "> " + accessToken.getToken());
			cTT.setAccessToken(accessToken);

			// Hide login button

			// Show Update Twitter

			// Getting user details from twitter
			// For now i am getting his name only
			long userID = accessToken.getUserId();
			User user = cTT.getTwitter().showUser(userID);
			String username = user.getName();
			return accessToken.getToken();
			// Displaying in xml ui
			// lblUserName.setText(Html.fromHtml("<b>Welcome " + username
			// + "</b>"));
		} catch (Exception e) {
			// Check log for login errors
			Log.e("Twitter Login Error", "> " + e.getMessage());
		}

		return null;
		// TODO Auto-generated method stub

	}

}
