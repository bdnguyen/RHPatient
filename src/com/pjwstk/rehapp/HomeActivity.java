package com.pjwstk.rehapp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.pjwstk.rehapp.api.ApiClient;
import com.pjwstk.rehapp.model.Exercise;
import com.pjwstk.rehapp.parsers.ExerciseJSONParser;

public class HomeActivity extends ActionBarActivity {
	
	private static final String TAG = "HomeActivity";
	private static final int EXERCISE_DONE_REQUEST_CODE = 1;
	private static Context ct;
	
	private ArrayAdapter<Exercise> HLAdapter = null; 
	ListView list = null; 
	private List<Exercise> todayExercises = new ArrayList<>();
	private int currentIndex = -1;	

	// Callback interface that allows this to notify the 'Home' activity when
	// user clicks on a List Item
	public interface ListSelectionListener {
		public void onListSelection(int index);
	}	
	
	public static Context getAppContext(){
		return HomeActivity.ct;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
	    setContentView(R.layout.activity_home);
	    	    
	    android.app.ActionBar bar = getActionBar();
	    bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#069c88")));	    
	    bar.setDisplayHomeAsUpEnabled(false);
	    
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("EEEE, dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());    
        
//        int daysLeft = 8;
//        String homeIntro = String.format(getResources().getString(R.string.homeIntro), formattedDate, daysLeft);
        String homeIntro = String.format(getResources().getString(R.string.homeIntro), formattedDate);
        ((TextView)findViewById (R.id.homeIntroView)).setText(homeIntro);
                   	
        new LoadExercisesTask().execute("therapy/GetTodayAllExercises");
	}
	

	private void populateListViewHome() {
		HLAdapter = new HomeListAdapter();
		ListView list = (ListView) findViewById(R.id.exercisesListViewHome);
		list.setAdapter(HLAdapter);
	}
	
	// Inner class for the custom Adapter
	private class HomeListAdapter extends ArrayAdapter<Exercise> {
		public HomeListAdapter() {
			super(HomeActivity.this, R.layout.exercises_list_item_home, todayExercises);
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View itemView = convertView;
			if (itemView == null) {
				itemView = getLayoutInflater().inflate(R.layout.exercises_list_item_home, parent, false);
			}
					
			Exercise currentExercise = todayExercises.get(position);
			
			// Fill the view
			TextView exTitleTxt = (TextView) itemView.findViewById(R.id.exerciseTitleHomeView);
			exTitleTxt.setText(currentExercise.getTitle());
			CheckBox cb = (CheckBox) itemView.findViewById(R.id.homeCheckBox);
			cb.setChecked(currentExercise.isDoneToday());
			if (currentExercise.isDoneToday()){
				exTitleTxt.setTextColor(getResources().getColor(R.color.blueRHTheme));
			} else { 
				exTitleTxt.setTextColor(getResources().getColor(R.color.red));
			}
			return itemView;
		}
	}
	
	private void registerClickCallback() {
		ListView list = (ListView) findViewById(R.id.exercisesListViewHome);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
				if (currentIndex != position) {
					currentIndex = position;
				}
				Exercise clickedExercise = todayExercises.get(position);				
				Intent SingleExIntent = new Intent(getApplicationContext(),SingleExerciseActivity.class);
				SingleExIntent.putExtra("clickedExercise", clickedExercise);
				startActivityForResult(SingleExIntent,EXERCISE_DONE_REQUEST_CODE);
				overridePendingTransition(R.anim.slide_in_right, R.anim.hold);
						
			}
		});			
	}
	
	
    private class LoadExercisesTask extends AsyncTask<String, String, List<Exercise>>{

        @Override
        protected List<Exercise> doInBackground(String... params) {
        	String responseContent = ApiClient.httpGET(params[0]);
        	todayExercises = ExerciseJSONParser.parseFeed(responseContent);
            return todayExercises;
        }

        @Override
        protected void onPostExecute(List<Exercise> result) {                 	      	
        	if(result != null && !result.isEmpty()){
        		populateListViewHome();
        	} else Toast.makeText(getApplicationContext(), R.string.loadTodayExerciseFailMessage, Toast.LENGTH_SHORT).show();
        	registerClickCallback();
        }

    }
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == EXERCISE_DONE_REQUEST_CODE) {
	        if (resultCode == RESULT_OK) {
	        	Bundle res = data.getExtras();
	        	Exercise CK = res.getParcelable("clickedExercise");
	        	//update ex object
	        	todayExercises.get(currentIndex).setDoneToday(CK.isDoneToday());
	        	HLAdapter.notifyDataSetChanged();
	        }
	    }
	}
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main_activity_actions, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.action_home:
//	        	new LoadExercisesTask().execute("therapy/GetTodayAllExercises");
//	        	HLAdapter.notifyDataSetChanged();
	        	Intent refresh = new Intent(this, HomeActivity.class);
	        	startActivity(refresh);
	        	finish(); 
	            return true;
//	        case R.id.action_calendar:
//	        	Intent intent2 = new Intent(this,CalendarActivity.class);
//	        	intent2.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//	            startActivity(intent2);	         
//	            return true;
	        case R.id.action_notes:
	        	Intent intent3 = new Intent(this,NotesActivity.class);
	        	intent3.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
	            startActivity(intent3);
	            return true;
	        case R.id.action_logout:
	        	Intent login = new Intent(this, MainActivity.class);
	        	startActivity(login);
	        	finish(); 
	            return true;      
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}    

	
    @Override
    public void onBackPressed() {
            //super.onBackPressed();
    }	
	
	@Override
	protected void onResume() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onResume()");
		super.onResume();
	}	
}

