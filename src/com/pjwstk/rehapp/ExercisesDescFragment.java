package com.pjwstk.rehapp;

import android.app.Activity;
import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.pjwstk.rehapp.R;
//Several Activity and Fragment lifecycle methods are instrumented to emit LogCat output
//so you can follow the class' lifecycle
public class ExercisesDescFragment extends Fragment {
	private TextView mDescView = null;
	private ImageView mImgView = null;
	private int mCurrIdx = -1;
	private int mDescArrayLen = 0;
	private static final String TAG = "DescFragment";
	
	public int getShownIndex() {
		return mCurrIdx;
}
// Show the Desc string at position newIndex
public void showDescAtIndex(int newIndex) {
	if (newIndex < 0 || newIndex >= mDescArrayLen)
		return;
	mCurrIdx = newIndex;
	mDescView.setText(ExercisesActivity.mDescArray[mCurrIdx]);
	//For test and debug only, to be modified
	if (newIndex == 0) {
	mImgView = (ImageView) getView().findViewById(R.id.exImg);
	mImgView.setImageResource(R.drawable.leg4);
	} else {
		mImgView = (ImageView) getView().findViewById(R.id.exImg);
		mImgView.setImageDrawable(null);
	}
}

@Override
public void onCreate(Bundle savedInstanceState) {
	Log.i(TAG, getClass().getSimpleName() + ":onCreate()");
	super.onCreate(savedInstanceState);
	// Retain this Fragment across Activity reconfigurations
	setRetainInstance(true);
	setHasOptionsMenu(true);
}

// Called to create the content view for this Fragment
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
Bundle savedInstanceState) {
	Log.i(TAG, getClass().getSimpleName() + ":onCreateView()");
	// Inflate the layout defined in exercises_desc_fragment.xml
	// The last parameter is false because the returned view does not need to be attached to the container ViewGroup
	return inflater.inflate(R.layout.exercises_desc_fragment, container, false);
}
// Set up some information about the mDescView TextView
@Override
public void onActivityCreated(Bundle savedInstanceState) {
	Log.i(TAG, getClass().getSimpleName() + ":onActivityCreated()");
	super.onActivityCreated(savedInstanceState);
	mDescView = (TextView) getActivity().findViewById(R.id.exercisesDescView);
	mDescArrayLen = ExercisesActivity.mDescArray.length;
	showDescAtIndex(mCurrIdx);
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
