package com.gt.georgiatech;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;

@SuppressLint("SetJavaScriptEnabled")
public class BusSchedule_Auto extends Activity{

	WebView webView;
	String myProvider;
	String url = "http://www.nextbus.com";
	Button bBSBack, bBSForward;
	LocationManager locationManager;
	LocationListener locationListener;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.busschedule);
		setTitle("GT Bus Schedule");
		webView = (WebView) findViewById(R.id.wvBusSchedule);
		webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setGeolocationEnabled(true);
		webView.setWebChromeClient(new WebChromeClient(){
			@Override
	        public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
	            callback.invoke(origin, true, false);
	        }
		});
		webView.loadUrl(url);
	}

	@Override
	protected void onPause() {
		super.onPause();
		Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
		intent.putExtra("enabled", false);
		sendBroadcast(intent);
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
			Intent LoginIntent = new Intent(BusSchedule_Auto.this, Login.class);
			startActivity(LoginIntent);
			return true;
		case 1:
			Intent aboutIntent = new Intent(BusSchedule_Auto.this, About.class);
			startActivity(aboutIntent);
			return true;
		}
		return false;
	}
	
}
