package com.pjwstk.rehapp.api;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class HTTPRequestHandler {
	private String URI;
	private String method;
	Map<String,String> params = new HashMap<>();
	
	public void putParam(String key, String value){
		params.put(key, value);
	}
	
	public String getURI() {
		return URI;
	}
	public void setURI(String uRI) {
		URI = uRI;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Map<String, String> getParams() {
		return params;
	}
	public void setParams(Map<String, String> params) {
		this.params = params;
	}

}
