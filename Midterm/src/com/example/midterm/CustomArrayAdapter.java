package com.example.midterm;

import java.util.ArrayList;



import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
   Assignment Midterm
 * @author Sanika Joshi
 * CustomArrayAdapter.java
 */
public class CustomArrayAdapter extends ArrayAdapter<Movies>{
private MoviesActivity context;
int layoutResourceId;
int textresourceId;
ArrayList<Movies> objects=null;
ImageView  imgView;  

	public CustomArrayAdapter(MoviesActivity context, int resource,ArrayList<Movies> objects) {
		super(context, resource);
		this.context = context;
		this.layoutResourceId = resource;
		this.objects = objects;
		Log.d("CustomArrayAdapter","Was here!!");
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return objects.size();
	}

	

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
			 LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			 convertView = inflater.inflate(layoutResourceId, parent, false);

			 Movies movie = objects.get(position);
			 String url = movie.getPoster_thumbnail_link();
			 imgView = (ImageView) convertView.findViewById(R.id.imageView1);
			 (new ImageAsyncTask(imgView)).execute(url);
			 TextView titleTextView =(TextView) convertView.findViewById(R.id.textView3);
			 titleTextView.setText(movie.getTitle().trim());
			 TextView yearTextView =(TextView) convertView.findViewById(R.id.textView4);
			 yearTextView.setText(movie.getYear());
			 TextView mpaaRatingTextView = (TextView)convertView.findViewById(R.id.textView5);
			 mpaaRatingTextView.setText(movie.getMPAA_rating());
			 return convertView;
	}
 
	
	
}
