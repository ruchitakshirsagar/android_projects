package com.example.jsonparser;

import java.util.LinkedList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class FeaturesUtil {
  static class FeaturesJSONParser {
	  static LinkedList<Features> parse(String json) throws JSONException {
		   JSONObject featuresJSONObject = new JSONObject(json);
		   JSONObject innerJSONObject = new JSONObject();
		   JSONObject innerMostJSONObject = new JSONObject();
		   //JSONArray featuresJSONArray = new JSONArray(json);
			LinkedList<Features> features = new LinkedList<Features>();
			
			JSONArray	featuresJSONArray = featuresJSONObject.getJSONArray("children");
		    //outermost    
			for(int i=0 ; i< featuresJSONArray.length() ;i++) {
		            // ith object
				    featuresJSONObject = featuresJSONArray.getJSONObject(i);
		            JSONArray innerArray  = featuresJSONObject.getJSONArray("children");
		            for(int j=0 ; j <innerArray.length() ; j++) {
		            	innerJSONObject = innerArray.getJSONObject(j);
		            	JSONArray innermostArray = innerJSONObject.getJSONArray("children");
		            	for(int k=0 ; k < innermostArray.length();k++) {
		            		innerMostJSONObject = innermostArray.getJSONObject(k);
		            		Features featuresObj = new Features(innerMostJSONObject);
		            		features.add(featuresObj);
		            		Log.d("Features Object", featuresObj.toString());
		            	}
		            }
		        }
				
				
			for(int i=0 ; i < features.size() ; i++) {
			   Log.d("JSON array List", features.get(i).toString()); 
		 	}
			Log.d("FeaturesJSONArray Size", featuresJSONArray.length() + "");
			return features;
			
	  }
  }
}
