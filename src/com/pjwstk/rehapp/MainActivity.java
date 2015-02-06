package com.pjwstk.rehapp;



import java.io.IOException;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	    // Set ActionBar color
	    android.app.ActionBar bar = getActionBar();
	    bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#069c88")));
	    
	    EditText unameET = (EditText) findViewById(R.id.editTextUsername);
	    EditText pwET = (EditText) findViewById(R.id.editTextPassword);
	    uname = unameET.getText().toString();
	    pw = pwET.getText().toString();
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

	
	public void login(View view) throws IOException {
		String token = ConnectionWS.getAuthToken(uname, pw);
		if (token != null){
			PreferenceManager.getDefaultSharedPreferences(getBaseContext()).edit().putString("loginToken", token).commit();
			Intent intent = new Intent(this, HomeActivity.class);
			startActivity(intent);
		}
		else Toast.makeText(getApplicationContext(), R.string.loginFailMessage, Toast.LENGTH_SHORT).show();
	}

}
