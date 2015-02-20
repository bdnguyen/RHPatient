package com.pjwstk.rehapp;



import java.io.IOException;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.pjwstk.rehapp.api.ConnectionWS;



public class MainActivity extends ActionBarActivity {
	
	private String uname;	
	private String pw;
	protected String loginToken;
	private static Context ct;
	ProgressBar loginPB;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		MainActivity.ct = getApplicationContext();
	    android.app.ActionBar bar = getActionBar();
	    bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#069c88")));	    	    
	    
	    loginPB = (ProgressBar) findViewById(R.id.loginProgressBar);
	    loginPB.setVisibility(View.INVISIBLE);
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
	
	private boolean isOnline(){
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting())
			return true; 
		else return false;
	}
	
	private class loginTask extends AsyncTask<String, Void, Boolean>{
        @Override
        protected void onPreExecute() {
        	loginPB.setVisibility(View.VISIBLE);
        }
		
		@Override
		protected Boolean doInBackground(String... params) {
			try {
				loginToken = ConnectionWS.getAuthToken(uname, pw);
				return true;
			} catch (Exception e) {
				e.printStackTrace();	
				return false;
			} 
		}
		
        protected void onPostExecute(final Boolean result) {                      
        	
        	loginPB.setVisibility(View.INVISIBLE);
        	
        	if (result){
    			PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("loginToken", loginToken).commit();
    			Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
    			startActivity(intent);  			
    		}
    		else Toast.makeText(MainActivity.getAppContext(), R.string.loginFailMessage, Toast.LENGTH_SHORT).show();
        		
        }   
	}
	
	public void login(View view) throws IOException {
		if(isOnline()){
			EditText unameET = (EditText) findViewById(R.id.editTextUsername);
	    	EditText pwET = (EditText) findViewById(R.id.editTextPassword);
			uname = unameET.getText().toString().trim();
	    	pw = pwET.getText().toString().trim();
	    	
	    	new loginTask().execute();
		} else Toast.makeText(MainActivity.getAppContext(), R.string.networkFailMessage, Toast.LENGTH_SHORT).show();
	}

	
	@Override
	protected void onPause() {
		super.onPause();
	}	
	
}
