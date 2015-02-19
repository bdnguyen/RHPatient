package com.pjwstk.rehapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.pjwstk.rehapp.api.ApiClient;
import com.pjwstk.rehapp.api.HTTPRequestHandler;
import com.pjwstk.rehapp.model.Exercise;
import com.pjwstk.rehapp.parsers.ExerciseJSONParser;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class SingleExerciseActivity extends FragmentActivity {
	
	private static final String TAG = "SingleExerciseActivity";
	private static final String TAG_PROGRAMEXERCISE_ID = "ProgramExerciseId";	
	
		TextToSpeech ttsObj;
		TextView txtViewDesc;
		
		private List<Bitmap> exImages = new ArrayList<>();
	    private ViewPager mPager;
	    private PagerAdapter mPagerAdapter;
	    
	    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_single_exercise);
	        	      	        	   
		    android.app.ActionBar bar = getActionBar();
		    bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#069c88")));
		    bar.setDisplayHomeAsUpEnabled(false);
		    //bar.hide();
	        		    
		    // set Exercise title on actionBar and Desc
		    setTitleAndDesc();
	        
	        // Initiate Text to Speech	        
	        initiateTextToSpeech();
	        
	        //Handle buttons
	        Button doneBtn = (Button) findViewById(R.id.doneBtn);
	        Button notDoneBtn = (Button) findViewById(R.id.notDoneBtn);
	        Button textToSpeechBtn = (Button) findViewById(R.id.txtospBtn);
	        
	        doneBtn.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					new MarkExerciseTask().execute("Therapy/MarkAsDone");
					Bundle extras = getIntent().getExtras();
					Exercise CK = extras.getParcelable("clickedExercise");
					if ((CK != null) && (CK.isDoneToday() == false)){
			            CK.setDoneToday(true);					
					}
		            Intent intent = new Intent();
		            intent.putExtra("clickedExercise",CK);
		            setResult(RESULT_OK, intent);
					finish();
					overridePendingTransition(R.anim.hold, R.anim.slide_out_right);
				}	
	        });
	        
	        notDoneBtn.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					new MarkExerciseTask().execute("Therapy/MarkAsSkipped");
					Bundle extras = getIntent().getExtras();
					Exercise CK = extras.getParcelable("clickedExercise");
			        if (CK.isDoneToday()){
			        	CK.setDoneToday(false);
					}
		            Intent intent = new Intent();
		            intent.putExtra("clickedExercise",CK);
		            setResult(RESULT_OK, intent);
					finish();
					overridePendingTransition(R.anim.hold, R.anim.slide_out_right);
				}	    	        	
	        });
	        
	        textToSpeechBtn.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					speakText(v);
				}	        	
	        });
	        
	        // Instantiate a ViewPager and a PagerAdapter.
	        mPager = (ViewPager) findViewById(R.id.pager);
	        mPagerAdapter = new PhotoSlidePagerAdapter();
	        mPager.setAdapter(mPagerAdapter);
	        
	    }
	
	
	private class MarkExerciseTask extends AsyncTask<String, Void, Void>{
		@Override
		protected Void doInBackground(String... params) {
			Bundle extras = getIntent().getExtras();
			Exercise CK = extras.getParcelable("clickedExercise");
			HTTPRequestHandler req = new HTTPRequestHandler();
			req.setMethod("POST");
			req.setURI(params[0]);
			req.putParam(TAG_PROGRAMEXERCISE_ID,CK.getExID()+"");		
			ApiClient.httpPOST(req);
			return null;
		}		
	}

	
//	private class LoadImagesTask extends AsyncTask<String, String, List<Bitmap>{
//
//
//        @Override
//        protected List<Bitmap> doInBackground(String... params) {
//        	Bundle extras = getIntent().getExtras();
//			Exercise CK = extras.getParcelable("clickedExercise");
//			List<String> iU = CK.getImgURLs();
//			HTTPRequestHandler req = new HTTPRequestHandler();
//			req.setMethod("GETIMG");
//        	String responseContent = ApiClient.httpGET(params[0]);
//        	
//        	return res
//        }
//
//        @Override
//        protected void onPostExecute(List<Bitmap> result) {           
//        	if(result != null){
//        		populateListViewHome();
//        	}
//        }
//
//    }
	    
	private class PhotoSlidePagerAdapter extends PagerAdapter {		
			private int[] mImages = new int[] {
				R.drawable.ex_izo1,
				R.drawable.ex_izo2,
				R.drawable.ex_izo3,
				R.drawable.ex_izo4
			};

			@Override
			public int getCount() {
				return mImages.length;
			}
	
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == ((ImageView) arg1);
			}
	    
			@Override
			public Object instantiateItem(ViewGroup container, int position) {			 
					Context context = SingleExerciseActivity.this;
					ImageView imageView = new ImageView(context);
		//				 int padding = context.getResources().getDimensionPixelSize(
		//				 R.dimen.padding_medium);
		//				 imageView.setPadding(padding, padding, padding, padding);
					imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
					imageView.setImageResource(mImages[position]);
					((ViewPager) container).addView(imageView, 0);
				return imageView;
			}
			 
			@Override
			public void destroyItem(ViewGroup container, int position, Object object) {
			((ViewPager) container).removeView((ImageView) object);
			}			
	}
		
	private void setTitleAndDesc() {
        Bundle extras = getIntent().getExtras();
        if (extras != null){
	        Exercise CK = extras.getParcelable("clickedExercise");	        
	        txtViewDesc = (TextView) findViewById(R.id.singleExDesc);
	        if (CK != null){
	        	getActionBar().setTitle(CK.getTitle());
		        txtViewDesc.setText(CK.getDescription());
		    }
	    }			
	}
	
    public void speakText(View view){
	      String toSpeak = txtViewDesc.getText().toString();
	      ttsObj.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
    }
    
    public void initiateTextToSpeech(){
        ttsObj=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {     	     
      	  @Override
  	      public void onInit(final int status) {
      		 new Thread(new Runnable(){
      			 public void run(){
          	         if(status != TextToSpeech.ERROR){
          	        	 //ttsObj.setLanguage(new Locale("pl_PL"));
          	        	 ttsObj.setLanguage(Locale.ENGLISH);
          	        	 ttsObj.setSpeechRate((float)0.7);
          	         }	
      			 }				      
      		 }).start();
      	  }
  	 });
    }
	  
    @Override
    public void onBackPressed(){   	
        super.onBackPressed();
        overridePendingTransition(R.anim.hold, R.anim.slide_out_right);
    }
    
    
	@Override
	protected void onDestroy() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onDestroy()");
	    if(ttsObj !=null){
	        ttsObj.stop();
	        ttsObj.shutdown();
	    }
		super.onDestroy();
	}
	@Override
	protected void onPause() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onPause()");
	      if(ttsObj !=null){
	          ttsObj.stop();
//	          ttsObj.shutdown();
	      }
		super.onPause();
	}
	@Override
	protected void onRestart() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onRestart()");
		super.onRestart();
	}
	@Override
	protected void onResume() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onResume()");
		super.onResume();
	}
	@Override
	protected void onStart() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onStart()");
		super.onStart();
	}
	@Override
	protected void onStop() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onStop()");
		super.onStop();
	}	
}
