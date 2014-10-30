package com.example.rehapp;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.os.Build;

public class Schedule extends ActionBarActivity {

/*	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_schedule);	
		
	}*/
	
	WebView mWebView;
	
	@SuppressLint("SetJavaScriptEnabled")
	@Override

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_schedule);
		mWebView = (WebView) findViewById(R.id.scheduleWebView);
		// Set a kind of listener on the WebView so the WebView can intercept
		// URL loading requests if it wants to
		mWebView.setWebViewClient(new HelloWebViewClient());
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.loadUrl("http://www.google.com/calendar");
}

		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
				mWebView.goBack();
				return true;
			}
			return super.onKeyDown(keyCode, event);
		}
		
		private class HelloWebViewClient extends WebViewClient {
			private static final String TAG = "WebViewClient";;
			// Give application a chance to catch additional URL loading requests

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				Log.i(TAG, "About to load:" + url);
				view.loadUrl(url);
				return true;
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
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
	    switch (item.getItemId()) {
        case R.id.action_schedule:
            return true;
	    case R.id.action_home:
            Intent intent = new Intent(this,HomeActivity.class);
            startActivity(intent);
            return true;
        case R.id.action_exercises:
        	Intent intent2 = new Intent(this,Exercises.class);
            startActivity(intent2);
            return true;
        case R.id.action_photos:
        	Intent intent3 = new Intent(this,Photos.class);
            startActivity(intent3);
            return true;   
        default:
            return super.onOptionsItemSelected(item);
    }
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
/*	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_schedule,
					container, false);
			return rootView;
		}
	}
*/
}
