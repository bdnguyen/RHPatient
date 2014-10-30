package com.example.rehapp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import com.example.rehapp.ExercisesTitleFragment.ListSelectionListener;
//Several Activity lifecycle methods are instrumented to emit LogCat output
//so you can follow this class' lifecycle
public class Exercises extends ActionBarActivity implements
ListSelectionListener {
	public static String[] mTitleArray;
	public static String[] mDescArray;
	private ExercisesDescFragment mDescsFragment;
	private static final String TAG = "Exercises";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the string arrays with the titles and descs
		mTitleArray = getResources().getStringArray(R.array.ExerciseTitles);
		mDescArray = getResources().getStringArray(R.array.ExerciseDescs);
		setContentView(R.layout.exercises_main);
		// Get a reference to the QuotesFragment
		mDescsFragment = (ExercisesDescFragment) getFragmentManager().findFragmentById(R.id.descs);

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
	        	Intent intent = new Intent(this,Schedule.class);
	            startActivity(intent);        	
	            return true;
	        case R.id.action_home:	            
	        	Intent intent2 = new Intent(this,HomeActivity.class);
	            startActivity(intent2);        	
	            return true;   
	        case R.id.action_photos:
	        	Intent intent3 = new Intent(this,Photos.class);
	            startActivity(intent3);
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