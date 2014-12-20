package com.pjwstk.rehapp;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class CalendarActivity extends ActionBarActivity {

	private static final String TAG = "CalendarActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calendar);
		
	    // Set ActionBar color
	    android.app.ActionBar bar = getActionBar();
	    bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#069c88")));
//	    int titleId = getResources().getIdentifier("action_bar_title", "id", "android");
//	    TextView abTitle = (TextView) findViewById(titleId);
//	    abTitle.setTextColor(Color.WHITE);	    
	    bar.setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_activity_actions, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
        case R.id.action_home:
        	Intent intent = new Intent(this,HomeActivity.class);
        	intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
            return true;
        case R.id.action_calendar:
            return true;           
        case R.id.action_notes:
        	Intent intent3 = new Intent(this,NotesActivity.class);
        	intent3.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent3);
            return true;             
        default:
            return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	protected void onResume() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onResume()");
		super.onResume();
	}	
}
