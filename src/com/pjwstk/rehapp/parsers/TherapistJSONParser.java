package com.pjwstk.rehapp.parsers;

import org.json.JSONException;
import org.json.JSONObject;

import com.pjwstk.rehapp.model.Therapist;

public class TherapistJSONParser {
	public static Therapist parseFeed(String content) {	
		try {
				JSONObject jObj =  new JSONObject();
				Therapist tp = new Therapist("");
				tp.setName(jObj.getString("Name"));	
				
				return tp;
			
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}		
	}
}
