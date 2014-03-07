package com.ephron.sngtab;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {

	String linkedinToken;
	String twittertoken;
	String facebooktoken;
	public String getFacebooktoken() {
		return prefs.getString("facebooktoken", "");
	}

	public void setFacebooktoken(String facebooktoken) {
		this.facebooktoken = facebooktoken;
		prefs.edit().putString("facebooktoken", facebooktoken).commit();
	}

	public String getTwittertoken() {
		return prefs.getString("twittertoken", "");
	}

	public void setTwittertoken(String twittertoken) {
		this.twittertoken = twittertoken;
		prefs.edit().putString("twittertoken", twittertoken).commit();
	}

	SharedPreferences prefs;
	static SharedPrefManager sharedPrefMngr=null;

	public String getLinkedinToken() {
		return prefs.getString("linkedinToken", "");
	}

	public void setLinkedinToken(String linkedinToken) {
		this.linkedinToken = linkedinToken;
		prefs.edit().putString("linkedinToken", linkedinToken).commit();
	}

	private SharedPrefManager(Activity activity) {
		prefs = activity.getSharedPreferences("com.ephron.sngtab",
				Context.MODE_PRIVATE);

	}

	public static SharedPrefManager getInstance(Activity activity) {
		// TODO Auto-generated method stub
		if (sharedPrefMngr != null) {
			return sharedPrefMngr;
		} else
			return new SharedPrefManager(activity);

	}


}
