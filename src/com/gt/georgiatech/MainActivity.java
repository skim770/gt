package com.gt.georgiatech;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	String menu[] = {"Buzzport","T-Square","NextBus", "Mail", "Calendar", 
			"GPA Calculator", "Manage Buzzcard", "Stingerette", "Careers"};
	SharedPreferences info;
	Button bBuzzport, bTSquare, bNextBus, bMail, bCalendar, bGPA, bBuzzcard, bStingerette, bCritique, bCareers;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		info = getSharedPreferences("loginInfo", MODE_PRIVATE);
		bBuzzport.setOnClickListener(new clickListener("Buzzport"));
		bTSquare.setOnClickListener(new clickListener("TSquare"));
		bNextBus.setOnClickListener(new clickListener("BusSchedule_Auto"));
		bMail.setOnClickListener(new clickListener("Mail"));
		bCalendar.setOnClickListener(new clickListener("Calendar"));
		bGPA.setOnClickListener(new clickListener("GPA"));
		bBuzzcard.setOnClickListener(new clickListener("Buzzcard"));
		bStingerette.setOnClickListener(new clickListener("Stingerette"));
		bCritique.setOnClickListener(new clickListener("Critique"));
		bCareers.setOnClickListener(new clickListener("Careers"));
	}
	
	private class clickListener implements OnClickListener{
		String id;
		public clickListener(String id){
			this.id = id;
		}
		@Override
		public void onClick(View arg0) {
			try {
				Class ourClass = Class.forName("com.gt.georgiatech."+id);
				Intent intent = new Intent(MainActivity.this, ourClass);
//				Bundle infoBundle = new Bundle();
//				infoBundle.putString("un", info.getString("un", "").toString());
//				infoBundle.putString("pw", info.getString("pw", "").toString());
//				intent.putExtras(infoBundle);
				startActivity(intent);
			} catch(ClassNotFoundException e){
				e.printStackTrace();
			}
		}
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		String hasBeenShown = info.getString("shownOnce", "");
		if (!hasBeenShown.equalsIgnoreCase("true")){
			Intent introIntent = new Intent(MainActivity.this, Intro.class);
			startActivity(introIntent);
		} 
	}
	
	protected void onPause() {
		super.onPause();
		String hasBeenShown = info.getString("shownOnce", "");
		if (!hasBeenShown.equalsIgnoreCase("true")){
			this.finish();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		menu.add(0,0,0, "Login");
		menu.add(0,1,1, "About");
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
		case 0:
			Intent LoginIntent = new Intent(MainActivity.this, Login.class);
			startActivity(LoginIntent);
			return true;
		case 1:
			Intent aboutIntent = new Intent(MainActivity.this, About.class);
			startActivity(aboutIntent);
			return true;
		}
		return false;
	}
	
	private void init(){
		bBuzzport = (Button) findViewById(R.id.bBuzzport);
		bTSquare = (Button) findViewById(R.id.bTSquare);
		bNextBus = (Button) findViewById(R.id.bNextBus);
		bMail = (Button) findViewById(R.id.bMail);
		bCalendar = (Button) findViewById(R.id.bCalendar);
		bGPA = (Button) findViewById(R.id.bGPA);
		bBuzzcard = (Button) findViewById(R.id.bBuzzcard);
		bStingerette = (Button) findViewById(R.id.bStingerette);
		bCritique = (Button) findViewById(R.id.bCritique);
		bCareers = (Button) findViewById(R.id.bCareers);
	}

}
