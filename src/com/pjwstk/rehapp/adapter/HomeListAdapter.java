package com.pjwstk.rehapp.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.pjwstk.rehapp.HomeActivity;
import com.pjwstk.rehapp.R;
import com.pjwstk.rehapp.model.Exercise;

public class HomeListAdapter extends ArrayAdapter<Exercise>{
		
		private Context ctx;
		private List<Exercise> exerciseList;
		
		public HomeListAdapter(Context ctx, int resource, List<Exercise> objects) {
			super(ctx, resource, objects);
			this.ctx = ctx;
			this.exerciseList = objects;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			LayoutInflater inf = (LayoutInflater) ctx.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			View itemView = inf.inflate(R.layout.exercises_list_item_home, parent, false);
			
			

			Exercise currentExercise = exerciseList.get(position);
			
			// Fill the view
			TextView exTitleTxt = (TextView) itemView.findViewById(R.id.exerciseTitleHomeView);
			exTitleTxt.setText(currentExercise.getTitle());
			CheckBox cb = (CheckBox) itemView.findViewById(R.id.homeCheckBox);
			cb.setChecked(currentExercise.isDoneToday());
			if (currentExercise.isDoneToday()){
				exTitleTxt.setTextColor(HomeActivity.getAppContext().getResources().getColor(R.color.greenhospital));
			} else { 
				exTitleTxt.setTextColor(HomeActivity.getAppContext().getResources().getColor(R.color.red));
			}
			return itemView;
		}
	
}
