package com.example.rehapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class ExerciseDescListActivity extends ListActivity {
public void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
// Get the Intent that started this Activity
Intent intent = getIntent();
// Retrieve the Extra stored under the name TitlesListActivity.INDEX
String quote = intent.getStringExtra(ExerciseTitleListActivity.INDEX);
if (null != quote) {
// Set the list adapter for the ListView
// Discussed in more detail in the user interface classes lesson
setListAdapter(new ArrayAdapter<String>(ExerciseDescListActivity.this,
R.layout.list_exercises_layout, new String[] { quote }));
}
}
}