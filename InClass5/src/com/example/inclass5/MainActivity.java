package com.example.inclass5;

import java.util.ArrayList;

import com.example.inclass5.NewsUtil.newsUtil;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.os.Build;

public class MainActivity extends Activity implements newsUtil {
	ArrayList<News> news;
	ListView myListView;
	ArrayList<String> newsTitleList;
	ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		myListView = (ListView) findViewById(R.id.listView1);
		news = new ArrayList<News>();

		(new AsyncTaskClass(this))
				.execute("http://www.fifa.com/newscentre/photo/rss.xml");

		// ArrayAdapter<String> adapter = new
		// ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,news);
		// myListView.setAdapter(adapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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

	@Override
	public void setArrayList(ArrayList<News> arrayList) {
		news = arrayList;
		newsTitleList = new ArrayList<String>();
		Log.d("Inside Main Activity", news + "");
		for(int i=0;i<arrayList.size(); i++) {
		  newsTitleList.add(arrayList.get(i).getTitle());	
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, newsTitleList);
		myListView.setAdapter(adapter);
		
		this.myListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
         
				Log.d("News total object", news.get(position).toString() + position);
				Intent i = new Intent(MainActivity.this,PhotoActivity.class);
				i.putExtra("Title", news.get(position).getTitle().toString());
				i.putExtra("URL",news.get(position).getMediaContent().toString() );
				i.putExtra("Description",news.get(position).getMediaDescription().toString());
				startActivity(i);
			}

		});

	}
}
