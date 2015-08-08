package com.example.mapsdemo;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.Toast;

public class MainActivity extends Activity implements OnMenuItemClickListener,
		OnClickListener {
	GoogleMap gMap;
	Button button1;
	PolylineOptions rectOptions;
	LocationManager lm;
	String provider;
	Polyline polyline;
	Location l;
	Location myLastLocation;
	LocationListener locationListener;
	static int count = 1;
	boolean onTouchFlag = true;
	public PolylineOptions setPolyLine(double latitude, double longitude) {
		
		LatLng llng = new LatLng(latitude, longitude);
		rectOptions.add(llng, new LatLng(latitude + 10, longitude + 10))
				.width(5).color(Color.RED);
		Toast.makeText(MainActivity.this,
				"Added Longitude : " + latitude + "" + latitude, 1000).show();
		return rectOptions;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		rectOptions = new PolylineOptions();
		
		gMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
		gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		if(gMap == null)
			Log.d("GMAP is NULL",null);
		lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

		myLastLocation = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

		gMap.setMyLocationEnabled(true);
		gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(35.2270869,-80.8431267),13));

		locationListener = new MyLocationListener();
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10,locationListener);

		/*Toast.makeText(
				MainActivity.this,
				"Location Sanika : " + myLastLocation.getLongitude()
						+ "Lattitude Sanika " + myLastLocation.getLatitude(),
				2000).show();
		 */
		
		
		gMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {

			@Override
			public void onMapLongClick(LatLng point) {
				if(onTouchFlag) {
					gMap.addMarker(new MarkerOptions().position(point).title("Start Location"));
				Toast.makeText(MainActivity.this,
						"Tracking has started" + point.latitude + "Lattitude" + point.longitude, 2000).show();
				  onTouchFlag = false;
				} else if (!onTouchFlag) {
					
					gMap.addMarker(new MarkerOptions().position(point).title("End Location"));
					Toast.makeText(MainActivity.this,"Tracking has stopped" + point.latitude + "Lattitude" + point.longitude, 2000).show();
					lm.removeUpdates(locationListener);
					locationListener = null;
					
				}
			}
		});
		

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {

	}

	@Override
	public boolean onMenuItemClick(MenuItem item) {
		// TODO Auto-generated method stub
		return false;
	}

	private class MyLocationListener implements LocationListener {

		@Override
		public void onLocationChanged(Location location) {
			double lng = location.getLongitude();
			double lat = location.getLatitude();
			LatLng point = new LatLng(lat, lng);
			
			List<LatLng> points = polyline.getPoints();
			points.add(point);
			polyline = gMap.addPolyline(new PolylineOptions().width(5).color(Color.RED));
            polyline.setPoints(points);
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub

		}

	}

}
