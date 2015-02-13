package com.pjwstk.rehapp.parsers;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.pjwstk.rehapp.model.Note;

public class NoteJSONParser {
	
	public static List<Note> parseFeed(String content) {	
		try {
			JSONArray jArray = new JSONArray(content);
			List<Note> noteList = new ArrayList<>();
			
			for (int i = 0; i < jArray.length(); i++) {
				
				JSONObject jObj =  jArray.getJSONObject(i);
				Note note = new Note("","",true);
				note.setIssuer(jObj.getString("SendingTherapistUser"));
				note.setContent(jObj.getString("Content"));
				if(jObj.getString("SendingTherapistUser") != null){
					note.setfromTherapist(true);
				} else {
					note.setfromTherapist(false);
				}
				
				noteList.add(note);
						
			}
			return noteList;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
