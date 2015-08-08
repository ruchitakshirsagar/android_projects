package com.example.midterm;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

/**
   Assignment Midterm
 * @author Sanika Joshi 
 * ImageAsyncTask.java
 */
public class ImageAsyncTask extends AsyncTask<String, Void, Bitmap>
	{

	ImageView imgView;
	public ImageAsyncTask(ImageView imgView) {
		this.imgView = imgView;
	}
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
			super.onPostExecute(result);
			if(imgView !=null&&result!=null)
			{
				imgView.setImageBitmap(result);
			}
			Log.d("Created the image","Image");
		}
		
	

}
