package com.ephron.sngtab;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class LauncherActivity extends Activity {

	TextView socialcards,netcoins,people,settings,logout,firstname,lastname;;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
		
		socialcards=(TextView)findViewById(R.id.textViewSocialCards);
		netcoins=(TextView)findViewById(R.id.textViewnetcoins);
		people=(TextView)findViewById(R.id.textViewpeoples);
		settings=(TextView)findViewById(R.id.textViewsettings);
		logout=(TextView)findViewById(R.id.textViewlogout);
		
		firstname=(TextView)findViewById(R.id.textViewfirstname);
		lastname=(TextView)findViewById(R.id.textViewlastname);
		
		Typeface typeFace=Typeface.createFromAsset(getAssets(),"fonts/FuturaLT-Condensed.ttf");
		socialcards.setTypeface(typeFace);
		netcoins.setTypeface(typeFace);
		people.setTypeface(typeFace);
		settings.setTypeface(typeFace);
		logout.setTypeface(typeFace);
		firstname.setTypeface(typeFace);
		lastname.setTypeface(typeFace);
		
		findViewById(R.id.socailcardLayout).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				findViewById(R.id.socailcardLayout).setBackgroundResource(R.drawable.submenuhover);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.launcher, menu);
		return true;
	}

}
