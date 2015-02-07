package com.pjwstk.rehapp.api;



import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;

import org.json.JSONException;
import org.json.JSONObject;

import android.preference.PreferenceManager;


public class ConnectionWS {
	private String passwordtokeystore = "hJ4D2Vd6tc";
	private String privatekeypassword = "6v9TGy53gA";
	
	public static SSLContext certHandler(){
		SSLContext context = null;
		try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            InputStream certificateAuthorityInput = new BufferedInputStream(new FileInputStream("assets/RehabilitationAppCA.cer"));
            Certificate certificateAuthority = certificateFactory.generateCertificate(certificateAuthorityInput);

            // Now we should have certificate authority loaded - check by checking SubjectDN name

            certificateAuthorityInput.close();

            // Keystore containing ca
            String keyStoreType = KeyStore.getDefaultType();
            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", certificateAuthority);

			// to transfer from pfx to jks
            // keytool -importkeystore -srckeystore mypfxfile.pfx -srcstoretype pkcs12 -destkeystore clientcert.jks -deststoretype JKS
            KeyStore clientKeyStore = KeyStore.getInstance("JKS"); //pkcs12
            clientKeyStore.load(null, null);
            InputStream clientInputStream = new FileInputStream("assets/clientcert.jks"); //p12
            clientKeyStore.load(clientInputStream, "hJ4D2Vd6tc".toCharArray()); //passwordToKeystore

            // Trustmanager that trusts ca from keystore
            String trustManagerFactoryAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(trustManagerFactoryAlgorithm);
            trustManagerFactory.init(keyStore);

            // Key manager that trusts client certificate
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("Sunx509"); //X.509
            keyManagerFactory.init(clientKeyStore, "6v9TGy53gA".toCharArray()); //privateKeyPassword


            // We create ssl context that uses trustmanager
            context = SSLContext.getInstance("TLS");
            context.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);

            
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        }
		return context; 
	}
	
	
	public static String getAuthToken(String username, String password) throws IOException {
		String responseContent = null;
		String accessToken = null;	
		String uname = username;
		String pw = password;
			
            // Connection
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier(){
            	public boolean verify(String hostname, SSLSession session){
            		if(hostname.equals("172.21.40.69"))
            			return true;
            		return false;
            	}           	
            });
            
            URL url = new URL("https://172.21.40.69/token");
            HttpsURLConnection urlConnection = (HttpsURLConnection)url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setSSLSocketFactory(certHandler().getSocketFactory());
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            DataOutputStream writer = new DataOutputStream(urlConnection.getOutputStream());
            String Content = "grant_type=password"+"&username="+uname+"&password="+pw; //patient@pjwstk.edu.pl pw:Zg7e3T8F
            writer.writeBytes(Content);
            writer.flush();
            writer.close();
            
            //parse the response
            InputStream inputStream = null;
            if(urlConnection.getResponseCode() >= 400){
            	inputStream = urlConnection.getErrorStream();            	
            } else {
            	inputStream = urlConnection.getInputStream();
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
            
	
		try {
			JSONObject parsedObject = new JSONObject(responseContent);				
			accessToken = parsedObject.getString("access_token");
		} catch (JSONException e) {
			throw new RuntimeException(e);
		}
		System.out.println(accessToken);
		return accessToken;
    }	
}

