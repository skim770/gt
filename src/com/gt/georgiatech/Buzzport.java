package com.gt.georgiatech;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class Buzzport extends Activity{
	
	WebView webView;
	String url = "https://buzzport.gatech.edu/misc/preauth.html?";
	SharedPreferences info;
	Button bBPBack, bBPForward;
	
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.buzzport);
		setTitle("GT Buzzport");
		init();
		info = getSharedPreferences("loginInfo", MODE_PRIVATE);
		webView = (WebView) findViewById(R.id.wvBuzzport);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setBuiltInZoomControls(true);
		webView.getSettings().setUseWideViewPort(true);
		webView.getSettings().setLoadWithOverviewMode(true);
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
		Toast toast = Toast.makeText(getApplicationContext(), "Loading: Please Wait... (:", 4000);
		toast.show();
		bBPBack.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				if (webView.isFocused() && webView.canGoBack()){
					webView.goBack();
				}
			}
		});
		bBPForward.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				if (webView.isFocused() && webView.canGoForward()){
					webView.goForward();
				}
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		menu.add(0,0,0, "Login");
		menu.add(0,1,1, "About");
		return true;
	}
	
	private void init(){
		bBPBack = (Button) findViewById(R.id.bBPBack);
		bBPBack.setText("<");
		bBPForward = (Button) findViewById(R.id.bBPForward);
		bBPForward.setText(">");
	}
	
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
		case 0:
			Intent LoginIntent = new Intent(Buzzport.this, Login.class);
			startActivity(LoginIntent);
			return true;
		case 1:
			Intent aboutIntent = new Intent(Buzzport.this, About.class);
			startActivity(aboutIntent);
			return true;
		}
		return false;
	}

}
