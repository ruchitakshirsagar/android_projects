package com.example.hw3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import com.example.hw3.PhotoActivityAsyncTask.PhotoInterface;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ViewFlipper;

/**
   Assignment HW3
 * @author Sanika Joshi Ruchita Kshirsagar
 * PhotoActivity.java
 */
public class PhotoActivity extends Activity implements PhotoInterface {
	// to detect the gestures
	private GestureDetector gestureDetector;
	ViewFlipper vflipper;
	RelativeLayout layout1;
	View.OnTouchListener gestureListener;
	private static final int SWIPE_MIN_DISTANCE = 320;
	private static final int SWIPE_MAX_OFF_PATH = 250;
	private static final int SWIPE_THRESHOLD_VELOCITY = 200;
	Bitmap bm;
	ImageView image;
	private int photoNumber = 0;
	ArrayList<String> arrayList = null;
	String mode = "";
    static boolean isOnPause =false;
	final int cacheSize = (int) (Runtime.getRuntime().maxMemory() / 1024);

	private LruCache<String, Bitmap> mMemoryCache;

	public int getLruLength() {
		return mMemoryCache.size();
	}

	public long getLruSize() {
		Set<String> keys = mMemoryCache.snapshot().keySet();
		Iterator<String> iter = keys.iterator();
		long size = 0;
		while (iter.hasNext()) {
			size = size + mMemoryCache.get(iter.next()).getByteCount();
		}
		return size / 1024;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo);

		arrayList = new ArrayList<String>();
		layout1 = (RelativeLayout) findViewById(R.id.relativeLayout);
		// Use 1/8th of the available memory for this memory cache.
		// final int cacheSize = maxMemory / 8;
		// final int cacheSize = 1024*1024*4;
		mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {

			@Override
			protected int sizeOf(String key, Bitmap value) {
				// The cache size will be measured in kilobytes rather than
				// number of items.
				return value.getByteCount() / 1024;
			}

		};

		gestureDetector = new GestureDetector(this, new MyGestureDetector());
		gestureListener = new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (mode.equals("Photo_Display_Mode")) {
					gestureDetector.onTouchEvent(event);
					return true;
				} else if (mode.equals("SlideShow_Display_Mode")) {
					Log.d("Gesture Detector", "disabled");
					return false;
				}
				return false;

			}
		};

		if (getIntent().getExtras() != null) {
			mode = getIntent().getExtras().getString(MainActivity.DISPLAY_MODE);

		}

		vflipper = (ViewFlipper) findViewById(R.id.viewFlipper1);

		if (!mode.isEmpty()) {
			arrayList = getIntent().getExtras().getStringArrayList(
					"ListOfImageUrls");

			if (mode.equals("Photo_Display_Mode")) {
			} else if (mode.equals("SlideShow_Display_Mode")) {
				startSlideShow(arrayList, mode);
			}

		}

		image = new ImageView(getApplicationContext());
		image.setOnTouchListener(gestureListener);
		layout1.addView(image);
		

		vflipper.setOnTouchListener(gestureListener);
		
		// Disk LRU Cache

		if (!arrayList.isEmpty())
			(new PhotoActivityAsyncTask(PhotoActivity.this, mode))
					.execute(arrayList.get(0));

	}

	@Override
	public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
		if (getBitmapFromMemCache(key) == null) {
			if (getLruSize() + (bitmap.getByteCount() / 1024) < cacheSize) {
				mMemoryCache.put(key, bitmap);
				Log.d("SIZE", "Within Limit");
			} else {
				Log.d("SIZE", "Exceeded. LRU: " + getLruSize() + ", Bitmap: "
						+ bitmap.getByteCount() + ", Cache: " + cacheSize);
				mMemoryCache.evictAll();
			}
		}
	}

	@Override
	public Bitmap getBitmapFromMemCache(String key) {
		try {
			Log.d("Getting the Bitmap ", mMemoryCache.get(key) + "Key " + key);
		} catch (NullPointerException e) {

		}
		return mMemoryCache.get(key);
	}

	public void startSlideShow(ArrayList<String> listOfImages, String mode) {
		new PhotoActivityAsyncTask(PhotoActivity.this, mode, listOfImages)
				.execute(listOfImages.get(0));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.photo, menu);
		return true;
	}

	class MyGestureDetector extends SimpleOnGestureListener {
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			try {
				if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
					return false;
				//
				if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
						&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					if (arrayList != null) {
						if (photoNumber != 0) {
							photoNumber = photoNumber - 1;
							new PhotoActivityAsyncTask(PhotoActivity.this,
									mode, 0)
									.execute(arrayList.get(photoNumber));
						} else {
							photoNumber = arrayList.size() - 1;
							new PhotoActivityAsyncTask(PhotoActivity.this,
									mode, 0)
									.execute(arrayList.get(photoNumber));
						}
					}
					Log.d("GestureDetector", "LeftSwipe");
					// vflipper.showPrevious();
					return true;

				} else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
						&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					if (photoNumber < arrayList.size() - 1) {
						photoNumber = photoNumber + 1;
					} else {
						photoNumber = 0;
					}
					Log.d("GestureDetector", "RightSwipe: " + photoNumber);
					// 1 means Right
					new PhotoActivityAsyncTask(PhotoActivity.this, mode, 1)
							.execute(arrayList.get(photoNumber));

					
					return true;
				}
			} catch (Exception e) {
				// nothing
			}
			return false;
		}

	}

	@Override
	public PhotoActivity getContext() {
		return PhotoActivity.this;
	}

	@Override
	public void setBitMap(Bitmap bm) {
		this.bm = bm;
		Log.d("bm", this.bm.toString());
	}

	@Override
	protected void onPause() {
		super.onPause();
	    isOnPause = true;
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		isOnPause = false;
	}

}
