package com.example.rehapp;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Build;
import android.provider.MediaStore;

public class Photos extends ActionBarActivity {

	private static final int PICK_IMAGE = 1;
	ImageView imageView1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photos);
		
	    // Set ActionBar color
	    android.app.ActionBar bar = getActionBar();
	    bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#99CCFF")));
	    int titleId = getResources().getIdentifier("action_bar_title", "id", "android");
	    TextView abTitle = (TextView) findViewById(titleId);
	    abTitle.setTextColor(Color.WHITE);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
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
	    switch (item.getItemId()) {
	    case R.id.action_home:
        	Intent intent1 = new Intent(this,HomeActivity.class);
            startActivity(intent1);
        	return true;
        case R.id.action_schedule:
        	Intent intent2 = new Intent(this,Schedule.class);
            startActivity(intent2);
        	return true; 
	    case R.id.action_exercises:
        	Intent intent3 = new Intent(this,ExercisesActivity.class);
            startActivity(intent3);
        	return true;
	    case R.id.action_notes:
        	Intent intent4 = new Intent(this,DoctorNotes.class);
        	intent4.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent4);	            
            return true;	
        case R.id.action_photos:
            return true;
        default:
            return super.onOptionsItemSelected(item);
    }
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_photos,
					container, false);
			return rootView;
		}
	}
	
	public void choosePhotosGallery (View view){
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
	}
	
	public void takePhoto (View view){
		Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(intent, 0);
			  
	}
}
