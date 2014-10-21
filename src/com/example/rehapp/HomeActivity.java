package com.example.rehapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.example.rehapp.MainActivity.PlaceholderFragment;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
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

public class HomeActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	    // Get the message from the intent
	    //Intent intent = getIntent();
	    //String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

	    // Create the text view
	    //TextView textView = new TextView(this);
	    //textView.setTextSize(40);
	    //textView.setText(message);
	    
	    // Set view as the activity layout
	    setContentView(R.layout.fragment_home);
	    
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => "+c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = df.format(c.getTime());
        // formattedDate have current date/time
        //Toast.makeText(this, formattedDate, Toast.LENGTH_SHORT).show();


      // Display formattedDate value in TextView
        TextView txtDateView = new TextView(this);
        txtDateView.setText("TODAY: "+formattedDate);
        txtDateView.setGravity(Gravity.TOP);
        txtDateView.setTextSize(40);
        setContentView(txtDateView);
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
		   // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_home:
	            return true;
	        case R.id.action_schedule:	            
	        	//Intent intent = new Intent(this,Schedule.class);
	            //startActivity(intent);
	        	
	            return true;
	        case R.id.action_photos:
	        	//Intent intent2 = new Intent(this,Photos.class);
	            //startActivity(intent2);
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
			View rootView = inflater.inflate(R.layout.fragment_home,
					container, false);
			return rootView;
		}
	}
}

