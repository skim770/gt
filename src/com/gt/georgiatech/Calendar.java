package com.gt.georgiatech;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Calendar extends Activity{
	
	WebView webView;
	String url = "http://registrar.gatech.edu/calendar/";
	SharedPreferences info;
	
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendar);
		setTitle("GT Calendar");
		
		info = getSharedPreferences("loginInfo", MODE_PRIVATE);
		webView = (WebView) findViewById(R.id.wvCalendar);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl(url);
		webView.setWebViewClient(new WebViewClient(){
			public boolean shouldOverrideUrlLoading(WebView view, String url){
				view.loadUrl(url);
				return true;
			}
			public void onPageFinished(WebView webView, String url){
				String un = info.getString("un", "");
				String pw = info.getString("pw", "");
				webView.loadUrl("javascript: {" +
			            "document.getElementById('username').value = '"+un+"';" +
			            "document.getElementById('password').value = '"+pw+"';" +
			            "document.getElementById('fm1').submit(); };");
			}
		});
		Toast toast = Toast.makeText(getApplicationContext(), "Loading: Please Wait... (:", 3000);
		toast.show();
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
			Intent LoginIntent = new Intent(Calendar.this, Login.class);
			startActivity(LoginIntent);
			return true;
		case 1:
			Intent aboutIntent = new Intent(Calendar.this, About.class);
			startActivity(aboutIntent);
			return true;
		}
		return false;
	}

}
