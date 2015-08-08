package com.example.inclass5;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;





import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParserException;

import android.os.AsyncTask;

public class AsyncTaskClass extends AsyncTask<String, Void, ArrayList<News>> {

	MainActivity context;
	public AsyncTaskClass(MainActivity context) {
	  this.context = context;
	} 	
	
	@Override
	protected ArrayList<News> doInBackground(String... params) {
		URL url;
		try {
			url = new URL(params[0]);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.connect();
			int statusCode = con.getResponseCode();
			if(statusCode == HttpURLConnection.HTTP_OK){
				InputStream in = con.getInputStream();
				ArrayList<News> newsObject = NewsUtil.parseNews(in,context);
				return newsObject;
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	protected void onPostExecute(ArrayList<News> result) {
		super.onPostExecute(result);
       context.setArrayList(result);
       
	}

	
}
