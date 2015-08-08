package com.example.applefeed;

import java.util.ArrayList;




import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	ArrayList<Applications> applicationsList; 
	ArrayAdapter<String> arrayAdapter;
	ListView myListView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		applicationsList = new ArrayList<Applications>();
		myListView = (ListView) findViewById(R.id.listView1);
		
		
		String url = "http://itunes.apple.com/us/rss/topgrossingapplications/limit=100/xml";
		
		
		//Start the asynctask with the url here
		(new AppleAsyncTask(this)).execute(url);
		
		/*ArrayList<String> applicationsDetailsList = new ArrayList<String>();
		for(int i=0;i<applicationsList.size(); i++) {
			  applicationsDetailsList.add(applicationsList.get(i).getApp_name());	
		}*/
		
		
		
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

	
	
	public void setArrayList(ArrayList<Applications> result) {
	 this.applicationsList = result;	
	 CustomArrayAdapter adapter = new CustomArrayAdapter(this,R.layout.list_view_row_item,applicationsList);
	 myListView.setAdapter(adapter);
	 this.myListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				
			}

			
		});	
	}

}
