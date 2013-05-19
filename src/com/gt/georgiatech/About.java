package com.gt.georgiatech;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class About extends Activity{
	TextView tvAbout, tvVersion, tvDevHeader, tvDevelopers, tvGDHeader, tvGDmember, tvFeedbackHeader, tvEmail, tvDisclaimer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		init();
		tvVersion.setText("v1.1.0 (Beta)");
		tvDevelopers.setText("Sung Kim");
		tvGDmember.setText("Casey Chae");
		tvFeedbackHeader.setText("Send Feedbacks To");
		tvEmail.setText("skim928@gatech.edu");
		tvDisclaimer.setText("Disclaimer: Only feedbacks from emails that end with '@gatech.edu' will be considered.\n"+
				"- If it's UI related, attach a sketch of how this app should look like, and who knows (:");
	}
	
	private void init(){
		tvAbout = (TextView) findViewById(R.id.tvAbout);
		tvVersion = (TextView) findViewById(R.id.tvVersion);
		tvDevHeader = (TextView) findViewById(R.id.tvDevHeader);
		tvDevelopers = (TextView) findViewById(R.id.tvDevelopers);
		tvGDHeader = (TextView) findViewById(R.id.tvGDHeader);
		tvGDmember = (TextView) findViewById(R.id.tvGDmember);
		tvFeedbackHeader = (TextView) findViewById(R.id.tvFeedbackHeader);
		tvEmail = (TextView) findViewById(R.id.tvEmail);
		tvDisclaimer = (TextView) findViewById(R.id.tvDisclaimer);
	}
	
}
