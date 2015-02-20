package com.pjwstk.rehapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.pjwstk.rehapp.api.ApiClient;
import com.pjwstk.rehapp.api.HTTPRequestHandler;
import com.pjwstk.rehapp.model.Note;
import com.pjwstk.rehapp.model.Therapist;
import com.pjwstk.rehapp.parsers.NoteJSONParser;
import com.pjwstk.rehapp.parsers.TherapistJSONParser;

public class NotesActivity extends ActionBarActivity {
	
	private static final String TAG = "NoteActivity";
	private static final String TAG_RUSERID = "ReceivingUserId";
	private static final String TAG_CONTENT = "Content";
	private List<Note> notes = new ArrayList<>();	
	private Therapist tp = new Therapist("");
	
	ArrayAdapter<Note> noteAdapter;
	EditText editTextNote;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notes);
	    
	    android.app.ActionBar bar = getActionBar();
	    bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#069c88")));
	    bar.setDisplayHomeAsUpEnabled(true);
	        
        Button sendBtn = (Button) findViewById(R.id.sendNoteBtn);
        editTextNote = (EditText) findViewById(R.id.editTextNote);
        
        sendBtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				if (!editTextNote.getText().toString().isEmpty()) {
					new SendNoteTask().execute("message/create");
					notes.add(new Note(editTextNote.getText().toString().trim(),false));
					noteAdapter.setNotifyOnChange(true);
					editTextNote.setText("");	
				}							
			}	        	
        });
	    
        new LoadTherapistNameTask().execute("patient/GetTherapist");
        new LoadNotes().execute("message/getallforinbox");
		
	}
	
	private void populateListViewNote() {
		noteAdapter = new NoteListAdapter();
		ListView list = (ListView) findViewById(R.id.listViewNotes);
		list.setAdapter(noteAdapter);		
	}
	
	private class NoteListAdapter extends ArrayAdapter<Note>{
		
		private LinearLayout noteWrapper;
		
		public NoteListAdapter() {
			super(NotesActivity.this, R.layout.activity_notes, notes);
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
	
			View itemView = convertView;
			if (itemView == null) {
				itemView = getLayoutInflater().inflate(R.layout.notes_list_item, parent, false);
			}
			
			noteWrapper = (LinearLayout) itemView.findViewById(R.id.noteWrapper);
				
			Note currentNote = notes.get(position);
			
			// Fill the view
			TextView noteContent = (TextView) itemView.findViewById(R.id.noteContent);
			noteContent.setText(currentNote.getContent());
			
			noteContent.setBackgroundResource(currentNote.isfromTherapist() ? R.drawable.bubble_blue : R.drawable.bubble_grey1);
			noteWrapper.setGravity(currentNote.isfromTherapist() ? Gravity.LEFT : Gravity.RIGHT);
			
			return itemView;
		}
		// Prevent clicking bug on listview items
		@Override
		public boolean isEnabled(int position) {
		    return false;
		}
		
	}
	
	   private class LoadTherapistNameTask extends AsyncTask<String, String, String>{

	        @Override
	        protected void onPreExecute() {
	        	super.onPreExecute();
	        }

	        @Override
	        protected String doInBackground(String... params) {
	        	String responseContent = ApiClient.httpGET(params[0]);	        	
	            return responseContent;
	        }

	        @Override
	        protected void onPostExecute(String result) {           
	        	tp = TherapistJSONParser.parseFeed(result);
	    	    String therapistName = tp.getName();
	    	    if (therapistName != null){	    	   
	    	    	String noteIntro = String.format(NotesActivity.this.getResources().getString(R.string.noteIntro), therapistName);
	    	    	((TextView)findViewById (R.id.textViewNoteIntro)).setText(noteIntro);
	    	    } 
	    	}	

	    }
	   
	   private class LoadNotes extends AsyncTask<String, String, String>{

	        @Override
	        protected void onPreExecute() {
	        	super.onPreExecute();
	        }

	        @Override
	        protected String doInBackground(String... params) {
	        	String responseContent = ApiClient.httpGET(params[0]);	
	        	notes = NoteJSONParser.parseFeed(responseContent);
	        	Collections.reverse(notes);
	            return responseContent;
	        }

	        @Override
	        protected void onPostExecute(String result) {           
	        	if(result != null && !result.isEmpty()){
	        		populateListViewNote();
	        	} else Toast.makeText(getApplicationContext(), R.string.loadNoteFailMessage, Toast.LENGTH_SHORT).show();	   	    	
	    	}	

	    }
	
		private class SendNoteTask extends AsyncTask<String, Void, Void>{
			@Override
			protected Void doInBackground(String... params) {
					HTTPRequestHandler req = new HTTPRequestHandler();
					req.setMethod("POST");
					req.setURI(params[0]);
					req.putParam(TAG_RUSERID, notes.get(0).getSendingUserId());
					req.putParam(TAG_CONTENT, editTextNote.getText().toString());
					ApiClient.httpPOST(req);	
				return null;
			}
		} 	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_activity_actions, menu);
		return super.onCreateOptionsMenu(menu);
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
	        	Intent intent2 = new Intent(this,CalendarActivity.class);
	        	intent2.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
	            startActivity(intent2);
	            return true;
	        case R.id.action_notes:            
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
