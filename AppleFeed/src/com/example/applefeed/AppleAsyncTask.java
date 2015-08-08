package com.example.applefeed;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;




import android.os.AsyncTask;

public class AppleAsyncTask extends AsyncTask<String, Void, ArrayList<Applications>>{

	MainActivity context;
	public AppleAsyncTask(MainActivity context) {
	   this.context = context;
	}
	
	@Override
	protected ArrayList<Applications> doInBackground(String... params) {
		try {
			URL url = new URL(params[0]);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.connect();
			int statusCode = con.getResponseCode();
			
			if(statusCode == HttpURLConnection.HTTP_OK) {
				InputStream in = con.getInputStream();
				return AppleUtils.ApplePullParser.parseAppleFeed(in);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	protected void onPostExecute(ArrayList<Applications> result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		context.setArrayList(result);
	}

}
