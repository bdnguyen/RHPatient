package com.pjwstk.rehapp;
import android.app.Activity;
import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
public class ExercisesPhotosFragment extends Fragment {
private static final String TAG = "ExercisesPhotosFragment";
@Override
public void onCreate(Bundle savedInstanceState) {
	Log.i(TAG, getClass().getSimpleName() + ":onCreate()");
	super.onCreate(savedInstanceState);
	// Retain this Fragment across Activity reconfigurations
	setRetainInstance(true);
	setHasOptionsMenu(true);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	Bundle savedInstanceState) {
	Log.i(TAG, getClass().getSimpleName() + ":onCreateView()");
	// Inflate the layout defined in exercises_desc_fragment.xml
	// The last parameter is false because the returned view does not need to be attached to the container ViewGroup
	return inflater.inflate(R.layout.exercises_photos_fragment, container, false);
	}
	@Override
	public void onAttach(Activity activity) {
	Log.i(TAG, getClass().getSimpleName() + ":onAttach()");
	super.onAttach(activity);
	}
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	Log.i(TAG, getClass().getSimpleName() + ":onConfigurationChanged()");
	super.onConfigurationChanged(newConfig);
	}
	@Override
	public void onDestroy() {
	Log.i(TAG, getClass().getSimpleName() + ":onDestroy()");
	super.onDestroy();
	}
	@Override
	public void onDestroyView() {
	Log.i(TAG, getClass().getSimpleName() + ":onDestroyView()");
	super.onDestroyView();
	}
	@Override
	public void onDetach() {
	Log.i(TAG, getClass().getSimpleName() + ":onDetach()");
	super.onDetach();
	}
	@Override
	public void onPause() {
	Log.i(TAG, getClass().getSimpleName() + ":onPause()");
	super.onPause();
	}
	@Override
	public void onResume() {
	Log.i(TAG, getClass().getSimpleName() + ":onResume()");
	super.onResume();
	}
	@Override
	public void onStart() {
	Log.i(TAG, getClass().getSimpleName() + ":onStart()");
	super.onStart();
	}
	@Override
	public void onStop() {
	Log.i(TAG, getClass().getSimpleName() + ":onStop()");
	super.onStop();
	}
}