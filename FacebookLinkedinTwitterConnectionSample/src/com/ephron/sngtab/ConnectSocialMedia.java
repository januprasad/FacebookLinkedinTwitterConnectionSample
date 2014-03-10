package com.ephron.sngtab;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;
import android.widget.Toast;

import com.ephron.sngtab.base.JenuineActivity;
import com.ephron.sngtab.linkedin.ConfigureLinkedinAuthTokens;
import com.ephron.sngtab.linkedin.LoginLinkedin;
import com.ephron.sngtab.twitter.ConfigureTwitterAuthTokens;
import com.ephron.sngtab.twitter.LoginTwitter;
import com.ephron.sngtab.util.Logger45;
import com.ephron.sngtab.util.UIMessage;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.FacebookError;
import com.facebook.android.Util;
import com.facebook.android.Facebook.DialogListener;

public class ConnectSocialMedia extends JenuineActivity implements OnTouchListener{
	private static final int LINKEDIN = 1;
	private static final int TWITTER = 2;
	private ConfigureLinkedinAuthTokens cLT;
	private SharedPrefManager manager;
	private ConfigureTwitterAuthTokens cTT;
	private LoginTwitter loginTwitter;
	private LoginLinkedin loginLinkedin;
//	private static final String APP_ID = "391200654345659";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.socialmediaconnect);
		
		manager = SharedPrefManager.getInstance(this);
		if (android.os.Build.VERSION.SDK_INT > 8) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		cLT = new ConfigureLinkedinAuthTokens();
		cTT = new ConfigureTwitterAuthTokens(this);
		loginTwitter = new LoginTwitter(cTT, this);
		loginLinkedin = new LoginLinkedin(cLT, this);

		
		
		
		if (manager.getLinkedinToken().length() >= 2) {
			findViewById(R.id.text3).setBackgroundResource(
					R.drawable.linkedin_coinnected);

		}

		if (manager.getTwittertoken().length() >= 2) {
			findViewById(R.id.text2).setBackgroundResource(
					R.drawable.twitter_connected);
		}

		
		if (manager.getFacebooktoken().length() >= 2) {
			findViewById(R.id.text1).setBackgroundResource(
					R.drawable.facebook_connected);
		}

		
		findViewById(R.id.text1).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// facebookif (manager.getTwittertoken().length() >= 2) {
				// findViewById(R.id.text3).setBackgroundResource(
				// R.drawable.linked_connect);
				if (manager.getFacebooktoken().length() >= 2) {
				Toast.makeText(
						getApplicationContext(),
						"Already connected (" + manager.getFacebooktoken()
								+ ") Longpress to Logout", Toast.LENGTH_SHORT).show();
			} else 
				fbLogin(manager);
			}
		});
		findViewById(R.id.text2).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// twitter

				if (manager.getTwittertoken().length() >= 2) {
					// findViewById(R.id.text3).setBackgroundResource(
					// R.drawable.linked_connect);
					Toast.makeText(
							getApplicationContext(),
							"Already connected (" + manager.getTwittertoken()
									+ ") Longpress to Logout", Toast.LENGTH_SHORT).show();
				} else {

					loadThread(TWITTER);
				}

			}
		});

		findViewById(R.id.text3).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// linkedin
				/*
				 * Toast.makeText(getApplicationContext(), "Link",
				 * Toast.LENGTH_SHORT).show();
				 */

				if (manager.getLinkedinToken().length() >= 2) {
					// findViewById(R.id.text3).setBackgroundResource(
					// R.drawable.linked_connect);
					Toast.makeText(
							getApplicationContext(),
							"Already connected (" + manager.getLinkedinToken()
									+ ") Longpress to Logout", Toast.LENGTH_SHORT).show();
				} else {
					loadThread(LINKEDIN);
				}
			}
		});
		
		findViewById(R.id.text1).setOnTouchListener(this);
		findViewById(R.id.text2).setOnTouchListener(this);
		findViewById(R.id.text3).setOnTouchListener(this);
		findViewById(R.id.text3).setOnLongClickListener(
				new View.OnLongClickListener() {

					@Override
					public boolean onLongClick(View v) {
						// TODO Auto-generated method stub
						manager.setLinkedinToken("");
						findViewById(R.id.text3).setBackgroundResource(
								R.drawable.linkedin_circle_color);
						return false;
					}
				});

		findViewById(R.id.text1).setOnLongClickListener(
				new View.OnLongClickListener() {

					@Override
					public boolean onLongClick(View v) {
						// TODO Auto-generated method stub
						manager.setFacebooktoken("");
						findViewById(R.id.text1).setBackgroundResource(
								R.drawable.facebook_circle_color);
						return false;
					}
				});

		
		findViewById(R.id.text2).setOnLongClickListener(
				new View.OnLongClickListener() {

					@Override
					public boolean onLongClick(View v) {
						// TODO Auto-generated method stub
						manager.setTwittertoken("");
						findViewById(R.id.text2).setBackgroundResource(
								R.drawable.twitter_circle_color);
						return false;
					}
				});

	

	}
	protected void loadThread(final int media) {
		// TODO Auto-generated method stub

		
		final ProgressDialog progressDialog = new ProgressDialog(context);
		progressDialog.setCancelable(false);
		progressDialog.setMessage("Loading....");
		progressDialog.show();

		Thread mThread = new Thread() {
			@Override
			public void run() {
				try {
					synchronized (this) {
						if (media == TWITTER)
							loginTwitter.loginToTwitter(message);
						if (media == LINKEDIN)
							loginLinkedin.loginToLinkedin();
						// Wait given period of time or exit on touch
						wait(1000);

					}
				} catch (InterruptedException ex) {
				}

				// finish();
				progressDialog.dismiss();

			}
		};
		mThread.start();

	}

	@Override
	protected void onNewIntent(Intent intent) {

		Uri uri = intent.getData();
		if (uri != null
				&& uri.toString().startsWith(
						ConfigureTwitterAuthTokens.TWITTER_CALLBACK_URL)) {

			String token = loginTwitter.getAccessTokenTwitter(uri);
			if (token != null) {
				manager.setTwittertoken(token + "");
				findViewById(R.id.text2).setBackgroundResource(
						R.drawable.twitter_connected);
			} else
				System.out.println("tkn becomes null chk again");

		} else if (intent.getData() != null) {
			String token = loginLinkedin.getAccessTokenTwitter(intent.getData());
			if(token!=null){
				findViewById(R.id.text3).setBackgroundResource(
						R.drawable.linkedin_coinnected);
				manager.setLinkedinToken(token + "");
			}
			
		}
	}
	@Override
	public boolean onTouch(View v, MotionEvent event) {

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN: {
			TextView view = (TextView) v;
			// overlay is black with transparency of 0x77 (119)
			view.getBackground().setColorFilter(0x77000000,
					PorterDuff.Mode.SRC_ATOP);
			view.invalidate();
			break;
		}
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL: {
			TextView view = (TextView) v;
			// clear the overlay
			// ss
			view.getBackground().clearColorFilter();
			view.invalidate();
		}
		}
		return false;
	}

	
}
