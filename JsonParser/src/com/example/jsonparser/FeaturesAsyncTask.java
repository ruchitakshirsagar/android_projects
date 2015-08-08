package com.example.jsonparser;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;

import android.os.AsyncTask;

public class FeaturesAsyncTask extends AsyncTask<String, Void, LinkedList<Features>> {

	@Override
	protected LinkedList<Features> doInBackground(String... params) {
		URL url;
		try {
			url = new URL(params[0]);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.connect();			
			int statusCode = con.getResponseCode();
			if (statusCode == HttpURLConnection.HTTP_OK) {
				InputStream in = con.getInputStream();
				String text = IOUtils.toString(in);				
				return FeaturesUtil.FeaturesJSONParser.parse(text);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
