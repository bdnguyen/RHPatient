package com.pjwstk.rehapp.parsers;

import org.json.JSONException;
import org.json.JSONObject;

import com.pjwstk.rehapp.model.Therapist;

public class TherapistJSONParser {
	public static Therapist parseFeed(String content) {	
		try {
				JSONObject jObj =  new JSONObject(content);
				Therapist tp = new Therapist("");
				tp.setName(jObj.optString("Name")+" "+jObj.optString("Surname"));				
				return tp;
			
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}		
	}
}
