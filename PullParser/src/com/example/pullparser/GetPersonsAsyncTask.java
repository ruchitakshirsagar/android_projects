package com.example.pullparser;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;



import android.os.AsyncTask;
import android.util.Log;

public class GetPersonsAsyncTask extends AsyncTask<String, Void, ArrayList<Person>> {

	@Override
	protected ArrayList<Person> doInBackground(String... params) {
	    try {
			
	    	URL url = new URL(params[0]);
		    HttpURLConnection con = (HttpURLConnection) url.openConnection();
		    con.setRequestMethod("GET");
		    con.connect();
		    int statusCode = con.getResponseCode();
		    if(statusCode == HttpURLConnection.HTTP_OK) {
		    	InputStream in = con.getInputStream();
		    	return PersonUtils.PersonsPullParser.parsePersons(in);
		    }
	    } catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}

	@Override
	protected void onPostExecute(ArrayList<Person> result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		if (result != null)
			Log.d("demo", result.toString());
		else
			Log.d("demo", "Null Person");

	}



}
