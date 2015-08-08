package com.example.midterm;



import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Build;

/**
Assignment Midterm
* @author Sanika Joshi 
* MovieActivity.java
*/
public class MovieActivity extends Activity {

	ImageView imgView, imgView2, imgView3;
	TextView textView,textView1,textView2,textView3,textView4;
	 String  url="";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movie);
		textView = (TextView) findViewById(R.id.textView2);
		textView1 = (TextView) findViewById(R.id.textView3);
		textView2 = (TextView) findViewById(R.id.textView4);
		textView3 = (TextView) findViewById(R.id.textView5);
		textView4 = (TextView) findViewById(R.id.textView6);
		
		imgView = (ImageView) findViewById(R.id.imageView1);
		imgView2 = (ImageView) findViewById(R.id.imageView2);
		imgView3 = (ImageView) findViewById(R.id.imageView3);

		imgView2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

		
		if (getIntent().getExtras() != null) {
			if (getIntent().getExtras().getString("MovieTitle") != null) {
              textView.setText(getIntent().getExtras().getString("MovieTitle"));    
			}

			if (getIntent().getExtras().getString("ImageUrl") != null) {
				url = getIntent().getExtras().getString("ImageUrl");
				new ImageAsyncTask(imgView).execute(url);
			}
			if (getIntent().getExtras().getString("mpaaRating") != null) {

				textView2.setText(getIntent().getExtras().getString("mpaaRating"));
			}
			if (getIntent().getExtras().getString("year") != null) {
                textView1.setText(getIntent().getExtras().getString("year")); 
			}

		}
		
		imgView3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				WebView webView = (WebView) findViewById(R.id.webView1);
				webView.loadUrl(url);
				Log.d("In Web View", url);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.movie, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
