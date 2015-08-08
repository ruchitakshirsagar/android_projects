package com.example.applefeed;

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

public class CustomArrayAdapter extends ArrayAdapter<Applications>{
	private MainActivity context;
    int layoutResourceId;
    int textresourceId;
   ArrayList<Applications> objects=null;
    
	public CustomArrayAdapter(MainActivity context, int resource,ArrayList<Applications> objects) {
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

	/*@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return objects.get(position);
	}*/

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
			 LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			 convertView = inflater.inflate(layoutResourceId, parent, false);

			 Applications applicationItem =  objects.get(position);
			 // Get the textview and set the text and the tag values
			 ImageView imgView = (ImageView) convertView.findViewById(R.id.imageView1);
			 TextView appNameTextView = (TextView) convertView.findViewById(R.id.textView1);
			 appNameTextView.setText(applicationItem.getApp_name());
			 TextView developerNameTextView = (TextView) convertView.findViewById(R.id.textView2);
			 developerNameTextView.setText(applicationItem.getDevelopername());
			 TextView releaseDate = (TextView) convertView.findViewById(R.id.textView3);
			 releaseDate.setText(applicationItem.getRelease_date());
			 TextView price = (TextView) convertView.findViewById(R.id.textView4);
			 price.setText(applicationItem.getPrice());
			 
			 TextView category = (TextView) convertView.findViewById(R.id.textView5);
			 Log.d("CustomArray", "Inflated The custom array");
			 return convertView;
	}
 
	
	
}
