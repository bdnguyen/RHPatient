package com.pjwstk.rehapp;



import java.io.IOException;

import org.json.JSONException;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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
	protected String loginToken;
	private static Context ct;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		MainActivity.ct = getApplicationContext();
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
	
	private boolean isOnline(){
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		if (ni != null && ni.isConnectedOrConnecting())
			return true; 
		else return false;
	}
	
	private class loginTask extends AsyncTask<String, Void, Boolean>{
		private ProgressDialog loginDialog = new ProgressDialog(MainActivity.this);
        @Override
        protected void onPreExecute() {
        	loginDialog.setMessage("Loading...Please be 'patient'.");
        	loginDialog.show();
        }
		
		@Override
		protected Boolean doInBackground(String... params) {
			try {
				loginToken = ConnectionWS.getAuthToken(uname, pw);
				return true;
			} catch (IOException e) {
				e.printStackTrace();
	        	if(loginDialog != null){
					loginDialog.dismiss();
				}  					
				return false;
			} 
		}
		
        protected void onPostExecute(final Boolean result) {                      
        	
        	if(loginDialog != null){
				loginDialog.dismiss();
			}  	
        	
        	if (result){
    			PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("loginToken", loginToken).commit();
    			Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
    			startActivity(intent);  			
    		}
    		else Toast.makeText(getApplicationContext(), R.string.loginFailMessage, Toast.LENGTH_LONG).show();
        		
        }   
	}
	
	public void login(View view) throws IOException {
		if(isOnline()){
			EditText unameET = (EditText) findViewById(R.id.editTextUsername);
	    	EditText pwET = (EditText) findViewById(R.id.editTextPassword);
			uname = unameET.getText().toString().trim();
	    	pw = pwET.getText().toString().trim();
	    	
	    	new loginTask().execute();
		} else Toast.makeText(getApplicationContext(), R.string.networkFailMessage, Toast.LENGTH_LONG).show();
	}

	
	@Override
	protected void onPause() {
		super.onPause();
	}	
	
}
