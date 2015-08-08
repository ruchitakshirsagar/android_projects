package com.example.midterm;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;

import android.os.AsyncTask;


/**
   Assignment Midterm
 * @author Sanika Joshi 
 * AsyncTaskClass.java
 */
public class AsyncTaskClass extends AsyncTask<String, Void, ArrayList<Movies>>{

	MoviesActivity context;
	public AsyncTaskClass(MoviesActivity movieActivity) {
		this.context = movieActivity;
	}
	
	@Override
	protected ArrayList<Movies> doInBackground(String... params) {
    try {
		URL url = new URL(params[0]);
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.connect();
		int statusCode = con.getResponseCode();
		if (statusCode == HttpURLConnection.HTTP_OK) {
			InputStream in = con.getInputStream();
			String text = IOUtils.toString(in);
			//Log.d("demo", text);
			return MoviesUtil.parseMovies(text);
		}
		
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		return null;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	
	@Override
	protected void onPostExecute(ArrayList<Movies> result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		context.setMoviesArrayList(result);
	}

}
