package com.example.hw3;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

/**
   Assignment HW3
 * @author Sanika Joshi Ruchita Kshirsagar
 * PhotoActivityAsyncTask.java
 */

public class PhotoActivityAsyncTask extends AsyncTask<String, Void, Bitmap> {
	InputStream is;
	BufferedInputStream bis;
	Bitmap bitmap;

	int index, direction;
	static int i = 0;
	PhotoInterface photoInterface;
	ProgressDialog progressdialog;
	String mode;
	static ArrayList<String> listOfImages;
	Timer t;
	PhotoActivityAsyncTask(PhotoInterface photoInterface, String mode) {
		this.mode = mode;
		this.photoInterface = photoInterface;
	}

	PhotoActivityAsyncTask(PhotoInterface photoInterface, String mode,
			int direction) {
		this.mode = mode;
		this.photoInterface = photoInterface;
		this.direction = direction;

	}

	PhotoActivityAsyncTask(PhotoInterface photoInterface, String mode,
			ArrayList<String> listOfImages) {
		this.mode = mode;
		this.photoInterface = photoInterface;
		this.listOfImages = listOfImages;
	}

	@Override
	protected Bitmap doInBackground(String... params) {
		try {
			if (!mode.equals("SlideShow_Display_Mode")) {
				final String imageKey = String.valueOf(params[0]);
				bitmap = photoInterface.getBitmapFromMemCache(imageKey.trim());
				Log.d("Test", photoInterface.getLruLength() + "");
				// Check disk cache in background thread
				Log.d("BITMAP inside PHOTOACTIVITY", bitmap + "");
				if (bitmap == null) {
					URLConnection conn = new URL(params[0]).openConnection();
					conn.connect();
					is = conn.getInputStream();
					bis = new BufferedInputStream(is);
					BitmapFactory.Options options = new BitmapFactory.Options();
					options.inSampleSize = 2;
					bitmap = BitmapFactory.decodeStream(bis, null, options);
					photoInterface.addBitmapToMemoryCache(imageKey.trim(),
							bitmap);
				}
				photoInterface.getContext().runOnUiThread(new Runnable() {
					@Override
					public void run() {
						ImageView imgV = new ImageView(photoInterface
								.getContext());
						imgV.setImageBitmap(bitmap);
						if (photoInterface.getContext().vflipper
								.getChildCount() > 1) {
							photoInterface.getContext().vflipper
									.removeViewAt(0);
						}
						photoInterface.getContext().vflipper.addView(imgV);
						if (direction == 1)
							photoInterface.getContext().vflipper.showNext();
						else if (direction == 0)
							photoInterface.getContext().vflipper.showPrevious();
					}
				});
			} else if (mode.equals("SlideShow_Display_Mode")) {
				t = new Timer();
               
				t.schedule(new TimerTask() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						String imageKey = listOfImages.get(i);
						bitmap = photoInterface.getBitmapFromMemCache(imageKey
								.trim());
						// Check disk cache in background thread
						if(t != null && photoInterface.getContext().isOnPause == true) {
							t.cancel();
							i=0;
						} else 	if (bitmap == null) {
							try {
								URLConnection conn = new URL(imageKey)
										.openConnection();
								conn.connect();
								is = conn.getInputStream();
							} catch (IOException e) {
								e.printStackTrace();
							}
							bis = new BufferedInputStream(is);
							BitmapFactory.Options options = new BitmapFactory.Options();
							options.inSampleSize = 2;
							bitmap = BitmapFactory.decodeStream(bis, null,
									options);
							photoInterface.addBitmapToMemoryCache(
									imageKey.trim(), bitmap);

						}
						photoInterface.getContext().runOnUiThread(
								new Runnable() {
									@Override
									public void run() {
										ImageView imgV = new ImageView(
												photoInterface.getContext());
										imgV.setImageBitmap(bitmap);
										if (photoInterface.getContext().vflipper
												.getChildCount() > 1) {
											photoInterface.getContext().vflipper
													.removeViewAt(0);

										}
										photoInterface.getContext().vflipper
												.addView(imgV);
										photoInterface.getContext().vflipper
												.showNext();
										Log.d("Bitmap image set", bitmap + "");

										if (i == (listOfImages.size() - 1)) {
											i = 0;
										} else {
											i++;
										}

									}

								});
					}
				}, 0, 2000);
			}
			
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		return bitmap;
	}

	static public interface PhotoInterface {
		public void setBitMap(Bitmap bm);

		public Bitmap getBitmapFromMemCache(String imageKey);

		public void addBitmapToMemoryCache(String key, Bitmap bitmap);

		public PhotoActivity getContext();

		public long getLruSize();

		public int getLruLength();

	}

	@Override
	protected void onPostExecute(Bitmap result) {
		super.onPostExecute(result);
		try {
			if(progressdialog != null)
				progressdialog.dismiss();
			
		} catch (Exception e) {

		}
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		if (!this.mode.equals("SlideShow_Display_Mode")) {
			progressdialog = new ProgressDialog(photoInterface.getContext());
			progressdialog.setMessage("Loading Image");
			progressdialog.setCancelable(false);
			progressdialog.setIndeterminate(true);
			progressdialog.show();
		}
	}
}
