package com.example.midterm;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
Assignment Midterm
* @author Sanika Joshi 
* MoviesUtil.java
*/


public class MoviesUtil {
		static ArrayList<Movies> parseMovies(String json) throws JSONException {
		JSONObject rottenTomatoJSONObject = new JSONObject(json);
		JSONArray rottenTomatorJSONArray  = rottenTomatoJSONObject.getJSONArray("movies");
		JSONObject newJSONObject = new JSONObject();
		Movies movies;
		ArrayList<Movies> moviesArrayList = new ArrayList<Movies>();
		
		for(int i=0 ; i < rottenTomatorJSONArray.length() ; i++) {
			newJSONObject = rottenTomatorJSONArray.getJSONObject(i);
		    movies = new Movies(newJSONObject);
		    moviesArrayList.add(movies);
			Log.d("JSON Object", movies.toString() + i);
		}
		
		
		return moviesArrayList;
		
		}
	
}
