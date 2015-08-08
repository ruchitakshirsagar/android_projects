package com.example.inclass5;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Build;

public class PhotoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo);
		String Title = getIntent().getStringExtra("Title");
		String Description = getIntent().getStringExtra("Description");
		String URL = getIntent().getStringExtra("URL");
		
		Log.d("tag", Title+" "+Description+" "+URL);
		TextView tv1 =(TextView) findViewById(R.id.textView1);
		tv1.setText(Title);
		TextView tv2 = (TextView) findViewById(R.id.textView2);
		tv2.setText(Description);
		new AsyncImage().execute(URL);
		
		
	}
	
	private class AsyncImage extends AsyncTask<String, Void, Bitmap>
	{

		@Override
		protected Bitmap doInBackground(String... params) {
			try {
				URL url = new URL(params[0]);
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				if(con.getResponseCode()==HttpURLConnection.HTTP_OK)
				{
					InputStream is = con.getInputStream();
					return BitmapFactory.decodeStream(is);
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
		protected void onPostExecute(Bitmap result) {
			ImageView iv = (ImageView)findViewById(R.id.imageView1);
			if(iv!=null&&result!=null)
			{
				iv.setImageBitmap(result);
			}
			super.onPostExecute(result);
		}
		
	}

}
