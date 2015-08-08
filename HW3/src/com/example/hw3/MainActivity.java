package com.example.hw3;

import java.util.ArrayList;

import com.example.hw3.AsynTaskMain.ListOfImagesInterface;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

/**
   Assignment HW3
 * @author Sanika Joshi Ruchita Kshirsagar
 * MainActivity.java
 */
public class MainActivity extends Activity implements ListOfImagesInterface {

	ArrayList<String> listOfImageURLs;
	Button b1, b2;
	static String DISPLAY_MODE;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		String url = "http://liisp.uncc.edu/~mshehab/api/photos.txt";
		new AsynTaskMain(MainActivity.this).execute(url);
		b1 = (Button) findViewById(R.id.button1);
		b2 = (Button) findViewById(R.id.button2);

		b1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						PhotoActivity.class);
				intent.putExtra("ListOfImageUrls", listOfImageURLs);
				intent.putExtra(DISPLAY_MODE, "Photo_Display_Mode");
				startActivity(intent);
			}
		});

		b2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						PhotoActivity.class);
				intent.putExtra("ListOfImageUrls", listOfImageURLs);
				intent.putExtra(DISPLAY_MODE, "SlideShow_Display_Mode");
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public MainActivity getContext() {
		return this;
	}

	@Override
	public void setImageList(ArrayList<String> listOfImages) {
		try {
			listOfImageURLs = new ArrayList<String>(listOfImages);
		} catch (Exception e) {
			Log.d("Exception is", e.getStackTrace().toString());
		}
	}

}
