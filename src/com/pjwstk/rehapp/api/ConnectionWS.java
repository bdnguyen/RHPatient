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

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import android.content.Context;
import android.content.res.AssetManager;

public class ConnectionWS {
	
//	public ConnectionWS(Context context){
//	}
	public static void main(String[] args) {
		try {

            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            InputStream certificateAuthorityInput = new BufferedInputStream(new FileInputStream("RehabilitationAppCA.cer"));
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
            KeyStore clientKeyStore = KeyStore.getInstance("pkcs12");
            clientKeyStore.load(null, null);
            InputStream clientInputStream = new FileInputStream("RehabilitationAppClient.p12");
            clientKeyStore.load(clientInputStream, "passwordtokeystore".toCharArray());

            // Trustmanager that trusts ca from keystore
            String trustManagerFactoryAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(trustManagerFactoryAlgorithm);
            trustManagerFactory.init(keyStore);

            // Key manager that trusts client certificate
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("X.509");
            keyManagerFactory.init(clientKeyStore, "privatekeypassword".toCharArray());


            // We create ssl context that uses trustmanager
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);

            // Connection
            URL url = new URL("https://172.21.40.69/");
            HttpsURLConnection urlConnection = (HttpsURLConnection)url.openConnection();
            urlConnection.setSSLSocketFactory(context.getSocketFactory());
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            DataOutputStream writer = new DataOutputStream(urlConnection.getOutputStream());
            writer.writeBytes("Content");
            writer.flush();
            writer.close();

            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuffer response = new StringBuffer();
            while((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();

            String responseContent = response.toString();



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


    }	
}
