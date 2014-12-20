package com.pjwstk.rehapp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.ProgressDialog;
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

import com.pjwstk.rehapp.model.Exercise;

public class HomeActivity extends ActionBarActivity {
	private static final String TAG = "HomeActivity";
	private static final int EXERCISE_DONE_REQUEST_CODE = 1;
	
	private ArrayAdapter<Exercise> HLAdapter = null; 
	ListView list = null; 
	private List<Exercise> todayExercises = new ArrayList();
	private int currentIndex = -1;	
	
	// Callback interface that allows this to notify the 'Home' activity when
	// user clicks on a List Item
	public interface ListSelectionListener {
		public void onListSelection(int index);
	}	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
	    // Set view as activity layout
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
	    setContentView(R.layout.activity_home);
	    
	    // Set ActionBar color
	    android.app.ActionBar bar = getActionBar();
	    bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#069c88")));	    
	    bar.setDisplayHomeAsUpEnabled(false);
	    
	    // Set Today's Date
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("EEEE, dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());    
        
        //Display formattedDate value in TextView
        int daysLeft = 2;
        String homeIntro = String.format(getResources().getString(R.string.homeIntro), formattedDate, daysLeft);
        ((TextView)findViewById (R.id.homeIntroView)).setText(homeIntro);
                   	
        //Populate Exercise list
        populateExerciseListHome();
        //Populate the List View
        populateListViewHome();
        //Handle clicks on List View
        registerClickCallback();
        
	}
	

	private void populateExerciseListHome() {
		todayExercises.add(new Exercise("Ćwiczenie izometryczne mięśnia czworogłowego (głowy przyśrodkowej)", "Leżąc tyłem lub siedząc z wyprostowanymi nogami podłóż nieco powyżej kolana zwinięty ręcznik. Nie odrywając pięty od podłoża naciśnij udem na ręcznik, tak, aby napiął się mięsień czworogłowy. Utrzymaj napięcie przez 5 sekund, a następnie rozluźnij nogę.", false));
		todayExercises.add(new Exercise("Ćwiczenie zgięcia stawu kolanowego", "Stań przy ścianie, tak aby plecy i pośladki całkowicie przylegały do ściany. Przesuń stopy około 15 cm od ściany. Powoli staraj się zrobić przysiad, tak aby stawy kolanowe nie przekroczyły linii stóp. Postaraj się zejść jak najniżej i wytrzymać w tej pozycji 10-15 sekund. Wróć do pozycji wyjściowej.",false));
		todayExercises.add(new Exercise("Ćwiczenie wyprostu stawu kolanowego", "Siedząc z wyprostowanymi nogami podłóż pod pietę złożony ręcznik. Rozluźnij mięśnie i pozwól, żeby staw kolanowy zaczął się prostować pod wpływem siły grawitacji. Podczas tego ćwiczenia napinaj mięsień czworogłowy. Postaraj się wytrzymać w tej pozycji 5 minut.",false));
		//todayExercises.add(new Exercise("Single knee extensions in sitting","Hold a straight leg for 5 sec. Return to start position.Repeat x 15", false ));
		//todayExercises.add(new Exercise("Hamstring catches \n(In lying)", "Lying on your front, bend your knee, bringing your heel towards your bottom. Return to start position. Repeat 15 times.", false));		
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
			CheckBox cb = (CheckBox) itemView.findViewById(R.id.homeCheckBox);
			cb.setChecked(currentExercise.isDoneToday());
			if (currentExercise.isDoneToday()){
				exTitleTxt.setTextColor(getResources().getColor(R.color.greenhospital));
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
			public void onItemClick(AdapterView<?> parent, View viewClicked,
					int position, long id) {
				if (currentIndex != position) {
					currentIndex = position;
				}
				Exercise clickedExercise = todayExercises.get(position);				
				Intent SingleExIntent = new Intent(getApplicationContext(),SingleExerciseActivity.class);
				SingleExIntent.putExtra("clickedExercise", clickedExercise);
				startActivityForResult(SingleExIntent,EXERCISE_DONE_REQUEST_CODE);
				overridePendingTransition(R.anim.slide_in_right, R.anim.hold);
				//new loadingDialogTask().execute();		
			}
		});			
	}
	
	
    private class loadingDialogTask extends AsyncTask<Void, Void, Void>{
        ProgressDialog Asyncdialog = new ProgressDialog(HomeActivity.this);

        @Override
        protected void onPreExecute() {
            //Asycdialog.setMessage(getString(R.string.loadingtype));
        	Asyncdialog.setMessage("Loading exercise..");
            Asyncdialog.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            //do some lengthy stuff

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {           
            Asyncdialog.dismiss();            
            super.onPostExecute(result);
        }

}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == EXERCISE_DONE_REQUEST_CODE) {
	        if (resultCode == RESULT_OK) {
	        	Bundle res = data.getExtras();
	        	Exercise CK = res.getParcelable("clickedExercise");
//	        	Toast.makeText(getApplicationContext(), ""+CK.isDoneToday(), Toast.LENGTH_LONG).show();
//	        	Toast.makeText(getApplicationContext(), ""+todayExercises.get(0).isDoneToday(), Toast.LENGTH_SHORT).show();
	        	//update ex object
	        	todayExercises.get(currentIndex).setDoneToday(CK.isDoneToday());
	        	HLAdapter.notifyDataSetChanged();
	        }
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
	    switch (item.getItemId()) {
	        case R.id.action_home:
	            return true;
	        case R.id.action_calendar:
	        	Intent intent2 = new Intent(this,CalendarActivity.class);
	        	intent2.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
	            startActivity(intent2);	         
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
    public void onBackPressed() {
            //super.onBackPressed();
    }	
	
	@Override
	protected void onResume() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onResume()");
		//overridePendingTransition(R.anim.hold, R.anim.slide_out_right);
		super.onResume();
	}	
}

