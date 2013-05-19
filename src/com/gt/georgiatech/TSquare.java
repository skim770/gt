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
import android.webkit.DownloadListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class TSquare extends Activity{
	
	WebView webView;
	String url = "http://t-square.gatech.edu/portal/pda/?force.login=yes";
	SharedPreferences info;
	Button bBack, bForward;
	
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tsquare);
		setTitle("GT T-Square");
		init();
		info = getSharedPreferences("loginInfo", MODE_PRIVATE);
		webView = (WebView) findViewById(R.id.wvTSquare);
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
		webView.setDownloadListener(new DownloadListener(){
			@Override
			public void onDownloadStart(String url, String userAgent, String contentDisposition, 
					String mimetype, long contentLength) {
				webView.loadUrl("https://docs.google.com/gview?embedded=true&url="+url);
			}
		});
		Toast toast = Toast.makeText(getApplicationContext(), "Loading: Please Wait... (:", 2000);
		toast.show();
		bBack.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				if (webView.isFocused() && webView.canGoBack()){
					webView.goBack();
				}
			}
		});
		bForward.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				if (webView.isFocused() && webView.canGoForward()){
					webView.goForward();
				}
			}
		});
	}
	
	private void init(){
		bBack = (Button) findViewById(R.id.bBack);
		bBack.setText("<");
		bForward = (Button) findViewById(R.id.bForward);
		bForward.setText(">");
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
			Intent LoginIntent = new Intent(TSquare.this, Login.class);
			startActivity(LoginIntent);
			return true;
		case 1:
			Intent aboutIntent = new Intent(TSquare.this, About.class);
			startActivity(aboutIntent);
			return true;
		}
		return false;
	}

}