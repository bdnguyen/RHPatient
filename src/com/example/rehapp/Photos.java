package com.example.rehapp;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.os.Build;
import android.provider.MediaStore;

public class Photos extends ActionBarActivity {

	private static final int PICK_IMAGE = 1;
	ImageView imageView1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photos);

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
        	Intent intent3 = new Intent(this,Exercises.class);
            startActivity(intent3);
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
