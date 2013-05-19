package com.gt.georgiatech;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends Activity{
	
	TextView tvInstructions, tvUser, tvPW, tvCUser, tvCPW, tvOptional;
	EditText etUser, etPW, etCUser, etCPW;
	Button bEnter;
	SharedPreferences info;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		setTitle("GT Login");
		init();
		info = getSharedPreferences("loginInfo", MODE_PRIVATE);
//		courseoffInfo = getSharedPreferences("courseoffInfo", MODE_PRIVATE);
		tvInstructions.setText("Please enter your Gatech Login Credentials");
		tvUser.setText("GT Username:");
		tvPW.setText("GT Password:");
//		tvOptional.setText("Optional Courseoff Login Info:");
//		tvCUser.setText("Courseoff Login Email:");
//		tvCPW.setText("Courseoff Password:");
		etUser.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
//		etCUser.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
		bEnter.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Editor editInfo = info.edit();
				editInfo.clear();
				editInfo.putString("un", etUser.getText().toString());
				editInfo.putString("pw", etPW.getText().toString());
				editInfo.putString("shownOnce", "true");
				editInfo.commit();
/*				Editor courseoffEdit = courseoffInfo.edit();
				courseoffEdit.clear();
				courseoffEdit.putString("c_un", etCUser.getText().toString());
				courseoffEdit.putString("c_pw", etCPW.getText().toString());
				courseoffEdit.commit();
*/				Intent loginIntent = new Intent(Login.this, MainActivity.class);
/*				Bundle loginInfoBundle = new Bundle();
				loginInfoBundle.putString("un", etUser.getText().toString());
				loginInfoBundle.putString("pw", etPW.getText().toString());
				loginIntent.putExtras(loginInfoBundle);
*/				startActivity(loginIntent);
			}
		});
		etUser.setText(info.getString("un", ""));
		etPW.setText(info.getString("pw",""));
//		etCUser.setText(courseoffInfo.getString("c_un", ""));
//		etCPW.setText(courseoffInfo.getString("c_pw", ""));
	}
	
	private void init(){
		tvInstructions = (TextView) findViewById(R.id.tvInstructions);
		tvUser = (TextView) findViewById(R.id.tvUser);
		etUser = (EditText) findViewById(R.id.etUser);
		tvPW = (TextView) findViewById(R.id.tvPW);
		etPW = (EditText) findViewById(R.id.etPW);
		bEnter = (Button) findViewById(R.id.bEnter);
/*		tvCUser = (TextView) findViewById(R.id.tvCUser);
		etCUser = (EditText) findViewById(R.id.etCUser);
		tvCPW = (TextView) findViewById(R.id.tvCPW);
		etCPW = (EditText) findViewById(R.id.etCPW);
		tvOptional = (TextView) findViewById(R.id.tvOptional);
*/	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		menu.add(0,0,0, "About");
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
		case 0:
			Intent aboutIntent = new Intent(Login.this, About.class);
			startActivity(aboutIntent);
			return true;
		}
		return false;
	}

	@Override
	protected void onPause() {
		super.onPause();
		this.finish();
	}
	
}
