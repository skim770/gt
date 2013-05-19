package com.gt.georgiatech;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class Critique extends Activity{
	
	WebView webView;
	String url = "http://critique.gatech.edu/";
	SharedPreferences info;
	Button bCCBack, bCCForward;
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.critique);
		setTitle("GT Course Critique");
		init();
		webView = (WebView) findViewById(R.id.wvCritique);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setBuiltInZoomControls(true);
		webView.getSettings().setUseWideViewPort(true);
		webView.loadUrl(url);
		webView.setWebViewClient(new WebViewClient(){
			public boolean shouldOverrideUrlLoading(WebView view, String url){
				view.loadUrl(url);
				return true;
			}
		});
		Toast toast = Toast.makeText(getApplicationContext(), "Loading: Please Wait... (:", 3000);
		toast.show();
		bCCBack.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				if (webView.isFocused() && webView.canGoBack()){
					webView.goBack();
				}
			}
		});
		bCCForward.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				if (webView.isFocused() && webView.canGoForward()){
					webView.goForward();
				}
			}
		});
	}
	
	private void init(){
		bCCBack = (Button) findViewById(R.id.bCCBack);
		bCCBack.setText("<");
		bCCForward = (Button) findViewById(R.id.bCCForward);
		bCCForward.setText(">");
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
			Intent LoginIntent = new Intent(Critique.this, Login.class);
			startActivity(LoginIntent);
			return true;
		case 1:
			Intent aboutIntent = new Intent(Critique.this, About.class);
			startActivity(aboutIntent);
			return true;
		}
		return false;
	}

}
