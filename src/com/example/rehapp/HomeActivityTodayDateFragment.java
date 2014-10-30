package com.example.rehapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;


import com.example.rehapp.MainActivity.PlaceholderFragment;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;


public class HomeActivityTodayDateFragment extends Fragment {
	
	private static final String TAG = "HomeActivityTodayDateFragment";
	
    Calendar c = Calendar.getInstance();
    //System.out.println("Current time => "+c.getTime());
    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    String formattedDate = df.format(c.getTime());
    // formattedDate have current date/time
    

	@Override
	public void onAttach(Activity activity) {
			Log.i(TAG, getClass().getSimpleName() + ":entered onAttach()");
			super.onAttach(activity);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.i(TAG, getClass().getSimpleName() + ":entered onCreate()");
		super.onCreate(savedInstanceState);
	}
	
	TextView txtDateView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	Bundle savedInstanceState) {
	    txtDateView.setText("TODAY: "+formattedDate);
	    txtDateView.setGravity(Gravity.TOP);
	    txtDateView.setTextSize(40);
	    
		return txtDateView;
	}
	
	//Toast.makeText(this, formattedDate, Toast.LENGTH_SHORT).show();
}	
