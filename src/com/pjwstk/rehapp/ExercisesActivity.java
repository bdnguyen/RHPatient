package com.pjwstk.rehapp;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;


import com.pjwstk.rehapp.R;
import com.pjwstk.rehapp.ExercisesTitleFragment.ListSelectionListener;
//Several Activity lifecycle methods are instrumented to emit LogCat output
//so you can follow this class' lifecycle
public class ExercisesActivity extends ActionBarActivity implements
ListSelectionListener {
	public static String[] mTitleArray;
	public static String[] mDescArray;
	private ExercisesDescFragment mDescsFragment;
	private static final String TAG = "Exercises";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    // Set ActionBar color
	    android.app.ActionBar bar = getActionBar();
	    bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#99CCFF")));
	    int titleId = getResources().getIdentifier("action_bar_title", "id", "android");
	    TextView abTitle = (TextView) findViewById(titleId);
	    abTitle.setTextColor(Color.WHITE);
	    
		
		// Get the resources titles and descs
		mTitleArray = getResources().getStringArray(R.array.ExerciseTitles);
		mDescArray = getResources().getStringArray(R.array.ExerciseDescs);
		setContentView(R.layout.activity_exercises);
		// Get a reference to the ExercisesDescFragment
		mDescsFragment = (ExercisesDescFragment) getFragmentManager().findFragmentById(R.id.descs);
		//
		//getActionBar().setDisplayHomeAsUpEnabled(true);
	}
	
	// Called when the user selects an item in the ExercisesTitleFragment
	@Override
	public void onListSelection(int index) {
		if (mDescsFragment.getShownIndex() != index) {
			// Tell the ExercisesDescFragment to show the desc string at position index
			mDescsFragment.showDescAtIndex(index);
		}
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main_activity_actions, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		   // Handle presses on the action bar items
	    switch (item.getItemId()) { 	
	        case R.id.action_exercises:
	            return true;
	        case R.id.action_schedule:	            
	        	Intent intent = new Intent(this,ScheduleActivity.class);
	        	intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
	            startActivity(intent);        	
	            return true;
	        case R.id.action_home:	            
	        	Intent intent2 = new Intent(this,HomeActivity.class);
	        	intent2.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
	            startActivity(intent2);        	
	            return true;
	        case R.id.action_notes:
	        	Intent intent3 = new Intent(this,NotesActivity.class);
	        	intent3.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
	            startActivity(intent3);	            
	            return true;          
	        case R.id.action_photos:
	        	Intent intent4 = new Intent(this,PhotosActivity.class);
	        	intent4.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
	            startActivity(intent4);
	            return true;       
	        default:
	            return super.onOptionsItemSelected(item);
	    }

	}    
	
@Override
protected void onDestroy() {
	Log.i(TAG, getClass().getSimpleName() + ":entered onDestroy()");
	super.onDestroy();
}
@Override
protected void onPause() {
	Log.i(TAG, getClass().getSimpleName() + ":entered onPause()");
	super.onPause();
}
@Override
protected void onRestart() {
	Log.i(TAG, getClass().getSimpleName() + ":entered onRestart()");
	super.onRestart();
}
@Override
protected void onResume() {
	Log.i(TAG, getClass().getSimpleName() + ":entered onResume()");
	super.onResume();
}
@Override
protected void onStart() {
	Log.i(TAG, getClass().getSimpleName() + ":entered onStart()");
	super.onStart();
}
@Override
protected void onStop() {
	Log.i(TAG, getClass().getSimpleName() + ":entered onStop()");
	super.onStop();
}
}