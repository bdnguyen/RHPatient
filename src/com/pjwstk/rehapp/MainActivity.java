package com.pjwstk.rehapp;



import java.io.IOException;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;
import android.preference.PreferenceManager;

import com.pjwstk.rehapp.R;
import com.pjwstk.rehapp.api.ConnectionWS;



public class MainActivity extends ActionBarActivity {
	
	private String uname;	
	private String pw;
	private String loginToken;
	private static Context ct;
	EditText et
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		MainActivity.ct = getApplicationContext();
	    // Set ActionBar color
	    android.app.ActionBar bar = getActionBar();
	    bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#069c88")));	    	    
	    
	}
	
	public static Context getAppContext(){
		return MainActivity.ct;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private class loginTask extends AsyncTask<Void, Void, Void>{
		
		@Override
		protected Void doInBackground(Void... params) {
			try {
				loginToken = ConnectionWS.getAuthToken(uname, pw);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
        protected void onPostExecute(Void result) {                      
        	if (!loginToken.isEmpty()){
    			PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("loginToken", loginToken).commit();
    			Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
    			startActivity(intent);
    		}
    		else Toast.makeText(getApplicationContext(), R.string.loginFailMessage, Toast.LENGTH_LONG).show();
        }   
	}
	
	public void login(View view) throws IOException {
		EditText unameET = (EditText) findViewById(R.id.editTextUsername);
    	EditText pwET = (EditText) findViewById(R.id.editTextPassword);
		uname = unameET.getText().toString().trim();
    	pw = pwET.getText().toString().trim();
    	
    	new loginTask().execute();
//    	String token = ConnectionWS.getAuthToken(uname, pw);
//  	
//    	if (!token.isEmpty()){
//			PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("loginToken", token).commit();
//	    	String u = "patient@pjwstk.edu.pl";
//	    	String p = "12345"; //Zg7e3T8F				
//	    	if(uname.equals(u) && pw.equals(p)){
//	    		Intent intent = new Intent(this, HomeActivity.class);
//	    		startActivity(intent);
//	    	}
//    	}
//    	else Toast.makeText(getApplicationContext(), R.string.loginFailMessage, Toast.LENGTH_LONG).show();			
	}

}
