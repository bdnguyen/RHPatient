package com.pjwstk.rehapp.api;
	
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.json.JSONArray;


public class ApiClient {
	
	public static void main(String[] args) {
		new ApiClient().getTodayExercises();
	}
		
	private void getTherapistName() {
		
	}
	
	private void getTodayExercises() {			
			String responseContent = null;
			String getTodayExercise_url = "https://172.21.40.69/api/exercise/GetTodayExercises";								
            URL url;
			try {
	           
				HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier(){
	            	public boolean verify(String hostname, SSLSession session){
	            		if(hostname.equals("172.21.40.69"))
	            			return true;
	            		return false;
	            	}           	
	            });
				
				url = new URL(getTodayExercise_url);
	            HttpsURLConnection httpsCon = (HttpsURLConnection)url.openConnection();
	            httpsCon.setRequestMethod("GET");
	            httpsCon.setRequestProperty("User-Agent", "Droidz");
	            httpsCon.setRequestProperty("Content-Type", "application/json");
	            httpsCon.setRequestProperty("Authorization", "Bearer "+ConnectionWS.getAuthToken("patient@pjwstk.edu.pl", "Zg7e3T8F"));
	            
	            
	            InputStream inputStream = null;
	            
	            if(httpsCon.getResponseCode() >= 400){
	            	inputStream = httpsCon.getErrorStream();            	
	            } else {
	            	inputStream = httpsCon.getInputStream();
	            }
	            
	            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
	            String line;
	            StringBuffer response = new StringBuffer();
	            while((line = rd.readLine()) != null) {
	                response.append(line);
	                response.append('\r');
	            }
	            rd.close();
	            responseContent = response.toString();
	            System.out.println(responseContent);
	                     
	            
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}	
//		return responseContent;
	}
}

