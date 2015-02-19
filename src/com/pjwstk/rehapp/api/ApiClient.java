package com.pjwstk.rehapp.api;
	
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.json.JSONArray;
import org.json.JSONObject;

import com.pjwstk.rehapp.MainActivity;

import android.content.Context;
import android.preference.PreferenceManager;


public class ApiClient {
	
	private static final String endpoint = "https://172.21.40.69/api/";
		
	public static String httpPOST(HTTPRequestHandler req) {
		
		String httpPOST_url = endpoint + req.getURI();
        URL url;
		try {
           
			HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier(){
            	public boolean verify(String hostname, SSLSession session){
            		if(hostname.equals("172.21.40.69"))
            			return true;
            		return false;
            	}           	
            });
			
			url = new URL(httpPOST_url);
            HttpsURLConnection httpsCon1 = (HttpsURLConnection)url.openConnection();
            httpsCon1.setRequestMethod(req.getMethod());
            httpsCon1.setRequestProperty("User-Agent", "Droidz");
            httpsCon1.setRequestProperty("Content-Type", "application/json");
            httpsCon1.setSSLSocketFactory(ConnectionWS.certHandler().getSocketFactory());
            httpsCon1.setRequestProperty("Authorization", "Bearer "+ConnectionWS.getAuthToken("test@test.pl", "r9ARj76B")); //patient@pjwstk.edu.pl   Zg7e3T8F	           
            //String tk = PreferenceManager.getDefaultSharedPreferences(MainActivity.getAppContext()).getString("loginToken", "");
            //httpsCon.setRequestProperty("Authorization", "Bearer "+ tk);
            if(req.getMethod().equals("POST")){
                DataOutputStream writer = new DataOutputStream(httpsCon1.getOutputStream());
                JSONObject jsonparams = new JSONObject(req.getParams());
                String content = jsonparams.toString();
                writer.writeBytes(content);
                writer.flush();
                writer.close();
            }

            
            InputStream inputStream = null;
            
            if(httpsCon1.getResponseCode() >= 400){
            	inputStream = httpsCon1.getErrorStream();            	
            } else {
            	inputStream = httpsCon1.getInputStream();
            }
            
            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuilder response = new StringBuilder();
            while((line = rd.readLine()) != null) {
                response.append(line + "\n");
            }
            rd.close();
            return response.toString();         
            
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	public static String httpGET(String URI) {
	
			String httpGET_url = endpoint + URI;
            URL url;
			try {
	           
				HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier(){
	            	public boolean verify(String hostname, SSLSession session){
	            		if(hostname.equals("172.21.40.69"))
	            			return true;
	            		return false;
	            	}           	
	            });
				
				url = new URL(httpGET_url);
	            HttpsURLConnection httpsCon = (HttpsURLConnection)url.openConnection();
	            httpsCon.setRequestMethod("GET");
	            httpsCon.setRequestProperty("User-Agent", "Droidz");
	            httpsCon.setRequestProperty("Content-Type", "application/json");
	            httpsCon.setSSLSocketFactory(ConnectionWS.certHandler().getSocketFactory());
	            httpsCon.setRequestProperty("Authorization", "Bearer "+ConnectionWS.getAuthToken("test@test.pl", "r9ARj76B")); 	           
	            //String tk = PreferenceManager.getDefaultSharedPreferences(MainActivity.getAppContext()).getString("loginToken", "");
	            //httpsCon.setRequestProperty("Authorization", "Bearer "+ tk);
	            
	            
	            InputStream inputStream = null;
	            
	            if(httpsCon.getResponseCode() >= 400){
	            	inputStream = httpsCon.getErrorStream();            	
	            } else {
	            	inputStream = httpsCon.getInputStream();
	            }
	            
	            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
	            String line;
	            StringBuilder response = new StringBuilder();
	            while((line = rd.readLine()) != null) {
	                response.append(line + "\n");
	            }
	            rd.close();
	    		return response.toString();         
	            
			} catch (MalformedURLException e) {
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
	}
}

