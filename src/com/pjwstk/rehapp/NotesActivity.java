package com.pjwstk.rehapp;

import java.util.ArrayList;

import java.util.List;
import java.util.Locale;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.pjwstk.rehapp.R;
import com.pjwstk.rehapp.model.*;

public class NotesActivity extends ActionBarActivity {
	
	private static final String TAG = "NoteActivity";
	private List<Note> notes = new ArrayList();	
	ArrayAdapter<Note> noteAdapter;
	EditText editTextNote;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notes);
	    // Set ActionBar color
	    android.app.ActionBar bar = getActionBar();
	    bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#069c88")));
	    int titleId = getResources().getIdentifier("action_bar_title", "id", "android");
	    TextView abTitle = (TextView) findViewById(titleId);
	    abTitle.setTextColor(Color.WHITE);
	    
	    //Handle button and edit_text
        Button sendBtn = (Button) findViewById(R.id.sendNoteBtn);
        editTextNote = (EditText) findViewById(R.id.editTextNote);
        
        sendBtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				if (!editTextNote.getText().toString().isEmpty()) {
					notes.add(new Note("self",editTextNote.getText().toString().trim(),false));
					noteAdapter.setNotifyOnChange(true);
					editTextNote.setText("");	
				}							
			}	        	
        });
	    
	    populateNoteList();
	    populateListViewNote();
		
	}


	private void populateNoteList() {
		notes.add(new Note("Zukowska","Hello there", true));
		notes.add(new Note("self","hi", false));
		notes.add(new Note("Zukowska","Bye", true));
		notes.add(new Note("Zukowska","dflkasdklfsdklfskdfdfsd", true));
		notes.add(new Note("Zukowska","Cleaning keyboard", true));
		notes.add(new Note("self","ok", false));
		notes.add(new Note("self","I have a problem", false));
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
			// Make sure to have a view to work with (can be given null)
			View itemView = convertView;
			if (itemView == null) {
				itemView = getLayoutInflater().inflate(R.layout.notes_list_item, parent, false);
			}
			
			noteWrapper = (LinearLayout) itemView.findViewById(R.id.noteWrapper);
			
			// Find the note to work with
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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
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
