package com.pjwstk.rehapp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.pjwstk.rehapp.R;
import com.pjwstk.rehapp.ExercisesTitleFragment.ListSelectionListener;
import com.pjwstk.rehapp.MainActivity.PlaceholderFragment;
import com.pjwstk.rehapp.model.Exercise;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class HomeActivity extends ActionBarActivity{
	
	private List<Exercise> todayExercises = new ArrayList(); 
	private int mCurrIdx = -1;
	private ListSelectionListener mListener = null;
	
	// Callback interface that allows this to notify the 'Home' activity when
	// user clicks on a List Item
	public interface ListSelectionListener {
		public void onListSelection(int index);
	}	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
	    // Set view as activity layout
	    setContentView(R.layout.activity_home);
	    
	    // Set ActionBar color
	    android.app.ActionBar bar = getActionBar();
	    bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#99CCFF")));
	    int titleId = getResources().getIdentifier("action_bar_title", "id", "android");
	    TextView abTitle = (TextView) findViewById(titleId);
	    abTitle.setTextColor(Color.WHITE);
	    
	    // Set Today's Date
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("EEEE, dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());    
        //Toast.makeText(this, formattedDate, Toast.LENGTH_SHORT).show();
        
        //Display formattedDate value in TextView
        Resources res = getResources();
        String homeIntro = String.format(res.getString(R.string.homeIntro), formattedDate);
        ((TextView)findViewById (R.id.homeIntroView)).setText(homeIntro);
               
    	if (-1 != mCurrIdx){
    		ListView list = (ListView) findViewById(R.id.exercisesListViewHome);
    		list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    		list.setItemChecked(mCurrIdx, true);
    	}
    	
        //Populate Exercise list
        populateExerciseListHome();
        //Populate the List View
        populateListViewHome();
        //Handle clicks on List View
        //registerClickCallback();

	}
	
	private void populateExerciseListHome() {
		todayExercises.add(new Exercise("Arm 1", "-Step one foot forward, letting that knee bend.\n -Lean onto the front leg, bringing your head and chest toward the corner.\n -Hold for 20-30 seconds. \n -Stand up straight and switch feet. \n -Repeat it on the other side. \n"));
		todayExercises.add(new Exercise("Leg 1", "Swing legs above head"));
		todayExercises.add(new Exercise("Neck 1", "Turn neck"));
		todayExercises.add(new Exercise("Arm 2", "Swing hands above head"));
		todayExercises.add(new Exercise("Leg 2", "Swing legs above head"));
		todayExercises.add(new Exercise("Neck 2", "Turn neck"));
		todayExercises.add(new Exercise("Arm 3", "Swing hands above head"));
		todayExercises.add(new Exercise("Leg 3", "Swing legs above head"));
		todayExercises.add(new Exercise("Neck 3", "Turn neck"));
		todayExercises.add(new Exercise("Arm 4", "Swing hands above head"));
		todayExercises.add(new Exercise("Leg 4", "Swing legs above head"));
		todayExercises.add(new Exercise("Neck 4", "Turn neck"));
		todayExercises.add(new Exercise("Arm 5", "Swing hands above head"));
		todayExercises.add(new Exercise("Leg 5", "Swing legs above head"));
		todayExercises.add(new Exercise("Neck 5", "Turn neck"));
	}
	
	private void populateListViewHome() {
		ArrayAdapter<Exercise> adapter = new HomeListAdapter();
		ListView list = (ListView) findViewById(R.id.exercisesListViewHome);
		list.setAdapter(adapter);
	}
	
	// Inner class for the custom Adapter
	private class HomeListAdapter extends ArrayAdapter<Exercise> {
		public HomeListAdapter() {
			super(HomeActivity.this, R.layout.exercises_list_item_home, todayExercises);
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// Make sure to have a view to work with (can be given null)
			View itemView = convertView;
			if (itemView == null) {
				itemView = getLayoutInflater().inflate(R.layout.exercises_list_item_home, parent, false);
			}
			
			// Find the exercise title to work with
			Exercise currentExercise = todayExercises.get(position);
			
			// Fill the view
			TextView exTitleTxt = (TextView) itemView.findViewById(R.id.exerciseTitleHomeView);
			exTitleTxt.setText(currentExercise.getTitle());
			
			return itemView;
		}
	}
	
	private void registerClickCallback() {
		ListView list = (ListView) findViewById(R.id.exercisesListViewHome);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View viewClicked,
					int position, long id) {
				Exercise clickedExercise = todayExercises.get(position);
//				String message = "Exericise number " + position
//								+ " with the name " + clickedExercise.getTitle();
//				Toast.makeText(HomeActivity.this, message, Toast.LENGTH_SHORT).show();
				
				if (mCurrIdx != position) {
					mCurrIdx = position;
					// Inform the 'Home' activity that the item in position has been selected
					mListener.onListSelection(position);
				}
				// Indicates the selected item has been checked
				ListView list = (ListView) findViewById(R.id.exercisesListViewHome);	
				list.setItemChecked(mCurrIdx, true);								
			}
		});			
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
	        	Intent intent1 = new Intent(this,ExercisesActivity.class);
	        	intent1.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
	            startActivity(intent1);
	            return true;
	        case R.id.action_schedule:
	        	Intent intent2 = new Intent(this,ScheduleActivity.class);
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
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.activity_home,
					container, false);
			return rootView;
		}
	}
}

