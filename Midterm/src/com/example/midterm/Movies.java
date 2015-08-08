package com.example.midterm;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
   Assignment Midterm
 * @author Sanika Joshi 
 * Movies.java
 */
public class Movies {

	String id,title,year,MPAA_rating,poster_thumbnail_link;

	public Movies(JSONObject newJSONObject) throws JSONException {
		JSONObject jsonObject = newJSONObject.getJSONObject("posters"); 
		if(jsonObject != null) {
			if(jsonObject.getString("thumbnail") != null) {
			 this.poster_thumbnail_link = jsonObject.getString("thumbnail").trim();
			}
		}
		if(newJSONObject.getString("id") != null) {
		this.id = newJSONObject.getString("id").trim();
		}
		if(newJSONObject.getString("title") != null) {
		this.title = newJSONObject.getString("title").trim();
		}
		if(newJSONObject.getString("year") != null) {
		this.year = newJSONObject.getString("year").trim();
		}
		if(newJSONObject.getString("mpaa_rating") != null) {
			this.MPAA_rating = newJSONObject.getString("mpaa_rating").trim();
		} 
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMPAA_rating() {
		return MPAA_rating;
	}

	public void setMPAA_rating(String mPAA_rating) {
		MPAA_rating = mPAA_rating;
	}

	@Override
	public String toString() {
		return "Movies [id=" + id + ", title=" + title + ", year=" + year
				+ ", MPAA_rating=" + MPAA_rating + "]";
	}

	public String getPoster_thumbnail_link() {
		return poster_thumbnail_link;
	}

	public void setPoster_thumbnail_link(String poster_thumbnail_link) {
		this.poster_thumbnail_link = poster_thumbnail_link;
	}

	
	
}
