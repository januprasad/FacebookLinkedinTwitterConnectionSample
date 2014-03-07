package com.ephron.sngtab.base;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.ephron.sngtab.R;
import com.ephron.sngtab.SharedPrefManager;
import com.ephron.sngtab.util.ConnectionDetector;
import com.ephron.sngtab.util.Logger45;
import com.ephron.sngtab.util.UIMessage;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.FacebookError;
import com.facebook.android.Util;
import com.facebook.android.Facebook.DialogListener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class JenuineActivity extends Activity {

	protected Context context;
	protected Activity activity;
	protected UIMessage message;
	private static final String APP_ID = "225563294304079";
	
	protected Facebook mFacebook = new Facebook(APP_ID);
	private SharedPrefManager manager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		context = this;
		activity = this;
		message = new UIMessage(context);

		ConnectionDetector cd = new ConnectionDetector(context);
		boolean isconnected = cd.isConnectingToInternet();
		if (!isconnected) {
			message.showAlertDialog(activity, "Internet Connection Error",
					"Please connect to working Internet connection", false);
		}

	}

	public UIMessage getMessage() {
		return message;
	}

	public void setMessage(UIMessage message) {
		this.message = message;
	}

	protected void fbLogin(SharedPrefManager manager) {
		this.manager=manager;
		/*
		 * mFacebook.authorize(context, new String[] {
		 * "publish_stream", "email", "user_groups", "read_stream",
		 * "user_about_me", "offline_access" }, Facebook.FORCE_DIALOG_AUTH, new
		 * LoginDialogListener());
		 */
		mFacebook.authorize(this, new String[] { "email",
				"user_likes", "user_location", "user_about_me",
				"user_subscriptions", "read_stream", "publish_actions",
				"publish_stream" }, Facebook.FORCE_DIALOG_AUTH,
				new LoginDialogListener());
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		mFacebook.authorizeCallback(requestCode, resultCode, data);
	}

	// DialogListener CLASS STATRT HERE.

	public final class LoginDialogListener implements DialogListener {
		public void onComplete(Bundle values) {
			new Fb().execute();

		}

		public void onFacebookError(FacebookError error) {
			Toast.makeText(
					context,
					"Something went wrong. Please try again" + error.toString(),
					Toast.LENGTH_LONG).show();
		}

		public void onError(DialogError error) {
			Toast.makeText(
					context,
					"Something went wrong. Please try again" + error.toString(),
					Toast.LENGTH_LONG).show();
		}

		public void onCancel() {
			Toast.makeText(context,
					"Something went wrong. Please try again", Toast.LENGTH_LONG)
					.show();
		}
	}

	private class Fb extends AsyncTask<Void, Void, JSONObject> {

		@Override
		protected JSONObject doInBackground(Void... params) {
			// TODO Auto-generated method stub
			JSONObject json = null;
			try {
			
				json = Util.parseJson(mFacebook.request("me"));
				return json;
			} catch (FacebookError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return json;
		}

		@Override
		protected void onPostExecute(JSONObject result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (result != null) {
				try {
					Logger45.log(result.toString());
					String facebookID = result.getString("id");
					String facebookEmail = result.getString("email");
					System.out.println("Facebook id..." + facebookID);
					System.out.println("Facebook emIL..." + facebookEmail);

					
					findViewById(R.id.text1).setBackgroundResource(
							R.drawable.facebook_connected);
//					String token=result.getString();
					Log.i("accessstoken",mFacebook.getAccessToken());
					manager.setFacebooktoken(mFacebook.getAccessToken()+"");
					List<NameValuePair> data = new ArrayList<NameValuePair>(2);
					data.add(new BasicNameValuePair("email", facebookEmail));
					data.add(new BasicNameValuePair("fbid", facebookID));
//					new ApiService(context, apiResponse, data,
//							"fbLogin").execute("");
				} catch (JSONException e) {
					Logger45.log("result exception  json fb");
					// TODO Auto-generated catch block
//					Log.showCenterToast(context,
//							"Please try again later!");
				}
			} else {
				Logger45.log("result null fb");
//				Log.showCenterToast(context,
//						"Please try again later!");
			}
		}
	}
}
