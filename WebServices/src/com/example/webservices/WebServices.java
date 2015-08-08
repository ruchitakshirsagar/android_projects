package com.example.webservices;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.widget.Toast;

public class WebServices extends AsyncTask<Void, Void, Void> {

	MainActivity activity;
	static Uri uri;
	static Bitmap bm;
	
	WebServices(MainActivity activity) {
		this.activity = activity;
	}

	@Override
	protected Void doInBackground(Void... params) {
		Bundle bundle = new Bundle();
		Message msg = new Message();
		BufferedReader in = null;
		StringBuffer sb = null;
		String urlString = "http://liisp.uncc.edu/~mshehab/mbapps/index.php";
		HttpClient client = new DefaultHttpClient();
		
		
	    InputStream is = null;
	    BufferedInputStream bis = null;
	   
		try {
			HttpGet request = new HttpGet(urlString);
			HttpResponse response = client.execute(request);
			if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){	
				 in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));	
				 sb = new StringBuffer("");	
				 String line = "";	
				 while((line = in.readLine()) != null){	
					 sb.append(line + "\n");	
				 }	
				 in.close();
			
			
				    URLConnection conn = new URL(sb.toString()).openConnection();
			        conn.connect();
			        is = conn.getInputStream();
			        bis = new BufferedInputStream(is, 8192);
			        bm = BitmapFactory.decodeStream(bis);
	    	        activity.runOnUiThread(new Runnable() {

		            @Override
		            public void run() {
		             activity.imgV.setImageBitmap(bm); 	
		            	
		            }
		        });
			} 
		}catch (Exception e) {
			
			}
		return null;
	}
}


