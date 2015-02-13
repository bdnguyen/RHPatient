package com.pjwstk.rehapp.parsers;
import java.util.List;
import java.util.ArrayList;

import com.pjwstk.rehapp.model.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ExerciseJSONParser {
	
	public static List<Exercise> parseFeed(String content) {	
		try {
			JSONArray exArray = new JSONArray(content);
			List<Exercise> exerciseList = new ArrayList<>();
			
			for (int i = 0; i < exArray.length(); i++) {
				
				JSONObject jObj =  exArray.getJSONObject(i);
				Exercise ex = new Exercise("","",false,null);
				ex.setTitle(jObj.getString("Title"));
				ex.setDescription(jObj.getString("Description"));
				ex.setDoneToday(false);
				//ex.setImgURLs(jObj.getJSONArray("PhotosPaths"));
				
				
				
				exerciseList.add(ex);
						
			}
			return exerciseList;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
