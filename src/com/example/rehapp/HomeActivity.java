package com.example.rehapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.example.rehapp.MainActivity.PlaceholderFragment;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

public class HomeActivity extends ActionBarActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	    // Set view as the activity layout
	    setContentView(R.layout.fragment_home);
	    // Set ActionBar color
	    android.app.ActionBar bar = getActionBar();
	    bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#99CCFF")));
	    int titleId = getResources().getIdentifier("action_bar_title", "id", "android");
	    TextView abTitle = (TextView) findViewById(titleId);
	    abTitle.setTextColor(Color.WHITE);
	    
	    // Set Today's Date
        Calendar c = Calendar.getInstance();
        //System.out.println("Current time => "+c.getTime());
        SimpleDateFormat df = new SimpleDateFormat("EEEE, dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());
        // formattedDate have current date/time
        //Toast.makeText(this, formattedDate, Toast.LENGTH_SHORT).show();
        
      // Display formattedDate value in TextView
        Resources res = getResources();
        String homeIntro = String.format(res.getString(R.string.homeIntro), formattedDate);
        ((TextView)findViewById (R.id.homeIntroView)).setText(homeIntro);
        //CharSequence styledText = Html.fromHtml(textDate);
        /*
        TextView txtDateView = new TextView(this);
        txtDateView.setText("TODAY: "+formattedDate);
        txtDateView.setGravity(Gravity.TOP);
        txtDateView.setTextSize(40);
        setContentView(txtDateView);
        */
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
	        	//Intent intent = new Intent(this, HomeActivity.class);
	        	//intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            return true;
	        case R.id.action_exercises:
	        	Intent intent1 = new Intent(this,Exercises.class);
	        	intent1.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
	            startActivity(intent1);
	            return true;
	        case R.id.action_schedule:
	        	Intent intent2 = new Intent(this,Schedule.class);
	        	intent2.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
	            startActivity(intent2);
	            return true;
	        case R.id.action_notes:
	        	Intent intent3 = new Intent(this,DoctorNotes.class);
	        	intent3.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
	            startActivity(intent3);	            
	            return true;      
	        case R.id.action_photos:
	        	Intent intent4 = new Intent(this,Photos.class);
	        	intent4.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
	            startActivity(intent4);
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

