package com.gt.georgiatech;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GPA extends Activity{

	DecimalFormat df;
	SharedPreferences gpaInfo;
	TextView tvCurrentGPA, tvCurrentCredits, tvCreditless, tvProjGPAh, tvProjGPA;
	TextView tvNameh, tvCredith, tvGradeh, tvGPADisclaimer;
	EditText etCurrentGPA, etCurrentCredits, etCreditless;
	Button bSubmit, bClear;
	EditText etName1, etCredit1, etGrade1, etName2, etCredit2, etGrade2;
	EditText etName3, etCredit3, etGrade3, etName4, etCredit4, etGrade4;
	EditText etName5, etCredit5, etGrade5, etName6, etCredit6, etGrade6;
	private static final float A = 4;
	private static final float B = 3;
	private static final float C = 2;
	private static final float D = 1;
	private static final float F = 0;
	double earnedGPA;
	int earnedCredits, creditsThatDontCount;
	InputMethodManager inputManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gpa);
		setTitle("GPA Calculator");
		init();
		tvGPADisclaimer.setText("Disclaimer: Only enter classes that count towards your GPA. "+
				"Be sure to add the credit hours that aren't added towards your grade above.");
		gpaInfo = getSharedPreferences("gpa", MODE_PRIVATE);
		setETs();
		bSubmit.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
				if (etCurrentGPA.getText().length()>0)
					earnedGPA = Double.parseDouble(etCurrentGPA.getText().toString());
				else {earnedGPA = 0;}
				if (etCurrentCredits.getText().length()>0)
					earnedCredits = Integer.parseInt(etCurrentCredits.getText().toString());
				else {earnedCredits = 0;}
				if (etCreditless.getText().length()>0)
					creditsThatDontCount = Integer.parseInt(etCreditless.getText().toString());
				else {creditsThatDontCount = 0;}
				int creditsThatCount = earnedCredits - creditsThatDontCount;
				int currentCredits = calculateCurrentCredits();
				int currentGPA = calculateCurrentGPA();
				int totalCredits = creditsThatCount + currentCredits;
				double totalGPA = ((creditsThatCount*earnedGPA)+currentGPA)/totalCredits;
				String finalGPA = df.format(totalGPA)+" / "+(totalCredits+creditsThatDontCount);
				tvProjGPA.setText(finalGPA);
				editGPA((float)earnedGPA, earnedCredits, creditsThatDontCount, finalGPA);
			}
		});
		bClear.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
				Editor editGPA = gpaInfo.edit();
				editGPA.clear();
				editGPA.commit();
				setETs();
				finish();
				startActivity(getIntent());
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
	
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
		case 0:
			Intent LoginIntent = new Intent(GPA.this, Login.class);
			startActivity(LoginIntent);
			return true;
		case 1:
			Intent aboutIntent = new Intent(GPA.this, About.class);
			startActivity(aboutIntent);
			return true;
		}
		return false;
	}
	
	private int calculateCurrentGPA() throws NumberFormatException{
		int currentGPA = 0;
		// Grade 1
		if (etGrade1.getText().length()>0){
			if (etGrade1.getText().toString().equals("A")){
				currentGPA += A * Integer.parseInt(etCredit1.getText().toString());
			} else if (etGrade1.getText().toString().equals("B")){
				currentGPA += B * Integer.parseInt(etCredit1.getText().toString());
			} else if (etGrade1.getText().toString().equals("C")){
				currentGPA += C * Integer.parseInt(etCredit1.getText().toString());
			} else if (etGrade1.getText().toString().equals("D")){
				currentGPA += D * Integer.parseInt(etCredit1.getText().toString());
			} else { currentGPA += F * Integer.parseInt(etCredit1.getText().toString()); }
		}
		// Grade 2
		if (etGrade2.getText().length()>0){
			if (etGrade2.getText().toString().equals("A")){
				currentGPA += A * Integer.parseInt(etCredit2.getText().toString());
			} else if (etGrade2.getText().toString().equals("B")){
				currentGPA += B * Integer.parseInt(etCredit2.getText().toString());
			} else if (etGrade2.getText().toString().equals("C")){
				currentGPA += C * Integer.parseInt(etCredit2.getText().toString());
			} else if (etGrade2.getText().toString().equals("D")){
				currentGPA += D * Integer.parseInt(etCredit2.getText().toString());
			} else { currentGPA += F * Integer.parseInt(etCredit2.getText().toString()); }
		}
		// Grade 3
		if (etGrade3.getText().length()>0){
			if (etGrade3.getText().toString().equals("A")){
				currentGPA += A * Integer.parseInt(etCredit3.getText().toString());
			} else if (etGrade3.getText().toString().equals("B")){
				currentGPA += B * Integer.parseInt(etCredit3.getText().toString());
			} else if (etGrade3.getText().toString().equals("C")){
				currentGPA += C * Integer.parseInt(etCredit3.getText().toString());
			} else if (etGrade3.getText().toString().equals("D")){
				currentGPA += D * Integer.parseInt(etCredit3.getText().toString());
			} else { currentGPA += F * Integer.parseInt(etCredit3.getText().toString()); }
		}
		// Grade 4
		if (etGrade4.getText().length()>0){
			if (etGrade4.getText().toString().equals("A")){
				currentGPA += A * Integer.parseInt(etCredit4.getText().toString());
			} else if (etGrade4.getText().toString().equals("B")){
				currentGPA += B * Integer.parseInt(etCredit4.getText().toString());
			} else if (etGrade4.getText().toString().equals("C")){
				currentGPA += C * Integer.parseInt(etCredit4.getText().toString());
			} else if (etGrade4.getText().toString().equals("D")){
				currentGPA += D * Integer.parseInt(etCredit4.getText().toString());
			} else { currentGPA += F * Integer.parseInt(etCredit4.getText().toString()); }
		}
		// Grade 5
		if (etGrade5.getText().length()>0){
			if (etGrade5.getText().toString().equals("A")){
				currentGPA += A * Integer.parseInt(etCredit5.getText().toString());
			} else if (etGrade5.getText().toString().equals("B")){
				currentGPA += B * Integer.parseInt(etCredit5.getText().toString());
			} else if (etGrade5.getText().toString().equals("C")){
				currentGPA += C * Integer.parseInt(etCredit5.getText().toString());
			} else if (etGrade5.getText().toString().equals("D")){
				currentGPA += D * Integer.parseInt(etCredit5.getText().toString());
			} else { currentGPA += F * Integer.parseInt(etCredit5.getText().toString()); }
		}
		// Grade 6
		if (etGrade6.getText().length()>0){
			if (etGrade6.getText().toString().equals("A")){
				currentGPA += A * Integer.parseInt(etCredit6.getText().toString());
			} else if (etGrade6.getText().toString().equals("B")){
				currentGPA += B * Integer.parseInt(etCredit6.getText().toString());
			} else if (etGrade6.getText().toString().equals("C")){
				currentGPA += C * Integer.parseInt(etCredit6.getText().toString());
			} else if (etGrade6.getText().toString().equals("D")){
				currentGPA += D * Integer.parseInt(etCredit6.getText().toString());
			} else { currentGPA += F * Integer.parseInt(etCredit6.getText().toString()); }
		}
		return currentGPA;
	}
	
	private int calculateCurrentCredits(){
		int result = 0;
		if (etCredit1.getText().length()>0)
			if (Integer.parseInt(etCredit1.getText().toString())>0)
				result += Integer.parseInt(etCredit1.getText().toString());
		if (etCredit2.getText().length()>0)
			if (Integer.parseInt(etCredit2.getText().toString())>0)
				result += Integer.parseInt(etCredit2.getText().toString());
		if (etCredit3.getText().length()>0)
			if (Integer.parseInt(etCredit3.getText().toString())>0)
				result += Integer.parseInt(etCredit3.getText().toString());
		if (etCredit4.getText().length()>0)
			if (Integer.parseInt(etCredit4.getText().toString())>0)
				result += Integer.parseInt(etCredit4.getText().toString());
		if (etCredit5.getText().length()>0)
			if (Integer.parseInt(etCredit5.getText().toString())>0)
				result += Integer.parseInt(etCredit5.getText().toString());
		if (etCredit6.getText().length()>0)
			if (Integer.parseInt(etCredit6.getText().toString())>0)
				result += Integer.parseInt(etCredit6.getText().toString());
		return result;
	}
	
	private void setETs(){
		if (gpaInfo.getFloat("currentGPA",0)!=0.0)
			etCurrentGPA.setText(gpaInfo.getFloat("currentGPA", 0)+"");
		if (gpaInfo.getInt("currentCredits",0)!=0)
			etCurrentCredits.setText(gpaInfo.getInt("currentCredits", 0)+"");
		if (gpaInfo.getInt("creditless", 0)!=0)
			etCreditless.setText(gpaInfo.getInt("creditless", 0)+"");
		etName1.setText(gpaInfo.getString("etName1", ""));
		etName2.setText(gpaInfo.getString("etName2", ""));
		etName3.setText(gpaInfo.getString("etName3", ""));
		etName4.setText(gpaInfo.getString("etName4", ""));
		etName5.setText(gpaInfo.getString("etName5", ""));
		etName6.setText(gpaInfo.getString("etName6", ""));
		if (gpaInfo.getInt("etCredit1",0)!=0)
			etCredit1.setText(gpaInfo.getInt("etCredit1", 0)+"");
		if (gpaInfo.getInt("etCredit2",0)!=0)
			etCredit2.setText(gpaInfo.getInt("etCredit2", 0)+"");
		if (gpaInfo.getInt("etCredit3",0)!=0)
			etCredit3.setText(gpaInfo.getInt("etCredit3", 0)+"");
		if (gpaInfo.getInt("etCredit4",0)!=0)
			etCredit4.setText(gpaInfo.getInt("etCredit4", 0)+"");
		if (gpaInfo.getInt("etCredit5",0)!=0)
			etCredit5.setText(gpaInfo.getInt("etCredit5", 0)+"");
		if (gpaInfo.getInt("etCredit6",0)!=0)
			etCredit6.setText(gpaInfo.getInt("etCredit6", 0)+"");
		etGrade1.setText(gpaInfo.getString("etGrade1", ""));
		etGrade2.setText(gpaInfo.getString("etGrade2", ""));
		etGrade3.setText(gpaInfo.getString("etGrade3", ""));
		etGrade4.setText(gpaInfo.getString("etGrade4", ""));
		etGrade5.setText(gpaInfo.getString("etGrade5", ""));
		etGrade6.setText(gpaInfo.getString("etGrade6", ""));
		tvProjGPA.setText(gpaInfo.getString("projGPA", ""));
	}
	
	private void editGPA(float currentGPA, int currentCredits, int creditless, String finalGPA){
		Editor editGPA = gpaInfo.edit();
		editGPA.clear();
		editGPA.putFloat("currentGPA", currentGPA);
		editGPA.putInt("currentCredits", currentCredits);
		editGPA.putInt("creditless", creditless);
		if (etName1.getText().length()>0){editGPA.putString("etName1", etName1.getText().toString());}
		if (etName2.getText().length()>0){editGPA.putString("etName2", etName2.getText().toString());}
		if (etName3.getText().length()>0){editGPA.putString("etName3", etName3.getText().toString());}
		if (etName4.getText().length()>0){editGPA.putString("etName4", etName4.getText().toString());}
		if (etName5.getText().length()>0){editGPA.putString("etName5", etName5.getText().toString());}
		if (etName6.getText().length()>0){editGPA.putString("etName6", etName6.getText().toString());}
		if (etCredit1.getText().length()>0){editGPA.putInt("etCredit1", Integer.parseInt(etCredit1.getText().toString()));}
		if (etCredit2.getText().length()>0){editGPA.putInt("etCredit2", Integer.parseInt(etCredit2.getText().toString()));}
		if (etCredit3.getText().length()>0){editGPA.putInt("etCredit3", Integer.parseInt(etCredit3.getText().toString()));}
		if (etCredit4.getText().length()>0){editGPA.putInt("etCredit4", Integer.parseInt(etCredit4.getText().toString()));}
		if (etCredit5.getText().length()>0){editGPA.putInt("etCredit5", Integer.parseInt(etCredit5.getText().toString()));}
		if (etCredit6.getText().length()>0){editGPA.putInt("etCredit6", Integer.parseInt(etCredit6.getText().toString()));}
		if (etGrade1.getText().length()>0){editGPA.putString("etGrade1", etGrade1.getText().toString());}
		if (etGrade2.getText().length()>0){editGPA.putString("etGrade2", etGrade2.getText().toString());}
		if (etGrade3.getText().length()>0){editGPA.putString("etGrade3", etGrade3.getText().toString());}
		if (etGrade4.getText().length()>0){editGPA.putString("etGrade4", etGrade4.getText().toString());}
		if (etGrade5.getText().length()>0){editGPA.putString("etGrade5", etGrade5.getText().toString());}
		if (etGrade6.getText().length()>0){editGPA.putString("etGrade6", etGrade6.getText().toString());}
		editGPA.putString("projGPA", finalGPA);
		editGPA.commit();
	}
	
	private void init(){
		df = new DecimalFormat("#.000");
		inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		tvCurrentGPA = (TextView) findViewById(R.id.tvCurrentGPA);
		etCurrentGPA = (EditText) findViewById(R.id.etCurrentGPA);
		tvCurrentCredits = (TextView) findViewById(R.id.tvCurrentCredits);
		etCurrentCredits = (EditText) findViewById(R.id.etCurrentCredits);
		tvProjGPAh = (TextView) findViewById(R.id.tvProjGPAh);
		tvProjGPA = (TextView) findViewById(R.id.tvProjGPA);
		bSubmit = (Button) findViewById(R.id.bSubmit);
		bClear = (Button) findViewById(R.id.bClear);
		tvNameh = (TextView) findViewById(R.id.tvNameh);
		tvCredith = (TextView) findViewById(R.id.tvCredith);
		tvGradeh = (TextView) findViewById(R.id.tvGradeh);
		etName1 = (EditText) findViewById(R.id.etName1);
		etName2 = (EditText) findViewById(R.id.etName2);
		etName3 = (EditText) findViewById(R.id.etName3);
		etName4 = (EditText) findViewById(R.id.etName4);
		etName5 = (EditText) findViewById(R.id.etName5);
		etName6 = (EditText) findViewById(R.id.etName6);
		etCredit1 = (EditText) findViewById(R.id.etCredit1);
		etCredit2 = (EditText) findViewById(R.id.etCredit2);
		etCredit3 = (EditText) findViewById(R.id.etCredit3);
		etCredit4 = (EditText) findViewById(R.id.etCredit4);
		etCredit5 = (EditText) findViewById(R.id.etCredit5);
		etCredit6 = (EditText) findViewById(R.id.etCredit6);
		etGrade1 = (EditText) findViewById(R.id.etGrade1);
		etGrade2 = (EditText) findViewById(R.id.etGrade2);
		etGrade3 = (EditText) findViewById(R.id.etGrade3);
		etGrade4 = (EditText) findViewById(R.id.etGrade4);
		etGrade5 = (EditText) findViewById(R.id.etGrade5);
		etGrade6 = (EditText) findViewById(R.id.etGrade6);
		tvGPADisclaimer = (TextView) findViewById(R.id.tvGPADisclaimer);
		tvCreditless = (TextView) findViewById(R.id.tvCreditless);
		etCreditless = (EditText) findViewById(R.id.etCreditless);
	}

}
