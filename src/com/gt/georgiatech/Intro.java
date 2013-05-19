package com.gt.georgiatech;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Intro extends Activity{
	
	TextView tvWelcome, tvSecurity, tvSkipDisclaimer;
	Button bProceed, bSkip;
	SharedPreferences info;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intro);
		setTitle("Welcome!");
		init();
		tvWelcome.setText("All-In-One Georgia Tech Tool");
		tvSecurity.setText("Please log in to gain easy access to anything that requires logging in. "+ 
				"You will have to relog in every once in a while for security purposes. "+
				"Have no fear, it will be easy!");
		tvSkipDisclaimer.setText("Your credentials are stored directly in the phone and are safe.\n"+
				"If you decide to skip this step, you can always come back through settings.");
		info = getSharedPreferences("loginInfo", MODE_PRIVATE);
		Editor editInfo = info.edit();
		editInfo.putString("shownOnce", "true");
		editInfo.commit();
		bProceed.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				Intent loginIntent = new Intent(Intro.this, Login.class);
				startActivity(loginIntent);
			}
		});
		bSkip.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				Intent skipIntent = new Intent(Intro.this, MainActivity.class);
				startActivity(skipIntent);
			}
		});
	}
	
	private void init(){
		tvWelcome = (TextView) findViewById(R.id.tvWelcome);
		tvSecurity = (TextView) findViewById(R.id.tvSecurity);
		bProceed = (Button) findViewById(R.id.bProceed);
		bSkip = (Button) findViewById(R.id.bSkip);
		tvSkipDisclaimer = (TextView) findViewById(R.id.tvSkipDisclaimer);
	}

	@Override
	protected void onPause() {
		super.onPause();
		this.finish();
	}
	
}