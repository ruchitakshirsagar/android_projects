package com.example.midterm;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
/**
Assignment Midterm
* @author Sanika Joshi 
* MoviesActivity.java
*/
public class MoviesActivity extends Activity {

	ListView myListView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movies);

		myListView = (ListView) findViewById(R.id.listView1);
		
		if (getIntent().getExtras() != null) {
			String movieType = getIntent().getExtras().getString("MOVIE_TYPE");
			Log.d("Movie type", movieType);
			if (movieType != null) {
				switch (movieType.trim()) {
				case "Box Office Movies":
					Log.d("BOX OFFICE MOVIES", "CLICKED");
					String url ="http://api.rottentomatoes.com/api/public/v1.0/lists/movies/box_office.json?apikey=p99jh2wtgfgbgyt67wpp57ck&page_limit=20";
					new AsyncTaskClass(MoviesActivity.this).execute(url);
					break;
				case "In Theatre Movies":
					Log.d("IN THEATRE MOVIES CLICKED", "CLICKED");
                    String url2= "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/in_theaters.json?apikey=p99jh2wtgfgbgyt67wpp57ck&page_limit=20";
					new AsyncTaskClass(MoviesActivity.this).execute(url2);
                    break;
				case "Opening Movies":
					Log.d("OPENING MOVIES", "CLICKED");
                    String url3="http://api.rottentomatoes.com/api/public/v1.0/lists/movies/opening.json?apikey=p99jh2wtgfgbgyt67wpp57ck&page_limit=20";
                	new AsyncTaskClass(MoviesActivity.this).execute(url3);
                    
                    break;
				case "UpComing Movies":
					Log.d("UPCOMING MOVIES", "CLICKED");
                    String url4="http://api.rottentomatoes.com/api/public/v1.0/lists/movies/upcoming.json?apikey=p99jh2wtgfgbgyt67wpp57ck&page_limit=20";
                	new AsyncTaskClass(MoviesActivity.this).execute(url4);
                    break;
				}
			}
		}
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_movies,
					container, false);
			return rootView;
		}
	}

	public void setMoviesArrayList(ArrayList<Movies> moviesList) {
		if(moviesList.size() > 0) {
			final ArrayList<Movies> moviesNewList;
			
			moviesNewList = moviesList;
			Log.d("Received the movies","Yes");
			CustomArrayAdapter adapter = new CustomArrayAdapter(this,R.layout.list_view_row_item,moviesList);
			myListView.setAdapter(adapter);
			myListView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Movies movie = moviesNewList.get(position);
					Intent i = new Intent(MoviesActivity.this,MovieActivity.class);
					i.putExtra("MovieTitle", movie.getTitle() );
					i.putExtra("ImageUrl",movie.getPoster_thumbnail_link());
					i.putExtra("mpaaRating",movie.getMPAA_rating());
					i.putExtra("year", movie.getYear());
					Log.d("ImageUrl",movie.getPoster_thumbnail_link());
					startActivity(i);
				}

			
			});
 
	}
	}

}
