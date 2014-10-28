package com.example.rehapp;

import com.example.rehapp.R;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ExerciseTitleListActivity extends ListActivity {
public static String[] mTitleArray;
public static String[] mQuoteArray;
public static final String INDEX = "index";
@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
// Get the string arrays with the titles and quotes
mTitleArray = getResources().getStringArray(R.array.ExerciseTitle);
mQuoteArray = getResources().getStringArray(R.array.ExerciseDesc);
// Set the list adapter for the ListView
// Discussed in more detail in the user interface classes lesson
setListAdapter(new ArrayAdapter<String>(ExerciseTitleListActivity.this,
R.layout.list_exercises_layout, ExerciseTitleListActivity.mTitleArray));
}
@Override
public void onListItemClick(ListView l, View v, int pos, long id) {
// Create implicitly Intent to start the QuoteListActivity class
Intent showItemIntent = new Intent(ExerciseTitleListActivity.this,
ExerciseDescListActivity.class);
// Add an Extra representing the currently selected position
// The name of the Extra is stored in INDEX
showItemIntent.putExtra(INDEX, mQuoteArray[pos]);
// Start the QuoteListActivity using Activity.startActivity()
startActivity(showItemIntent);
}
}
