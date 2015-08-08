package com.example.inclass4a;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	ProgressDialog progressDialog;
	Button b1;
	TextView tv1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		b1 = (Button) findViewById(R.id.button1);
		tv1 = (TextView) findViewById(R.id.textView1);
		b1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				(new HeavyWork()).execute();
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	class HeavyWork extends AsyncTask<Void, Integer, Double> {

		public double getNumber() {
			double value = 0.0d;
			value = (double) Math.random();
			return value;
		}

		// This method is executed by the child thread
		@Override
		protected Double doInBackground(Void... params) {
			double value=0 ;
			for (int i = 0; i < 1000; i++) {
				for (int j = 0; j < 1000; j++) {

				}
			 
				publishProgress((i + 1));
			}

			value = getNumber();
			return value;
		}

		// The following methods are executed by the UI Main thread
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			progressDialog = new ProgressDialog(MainActivity.this);
			progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			progressDialog.setMax(100);
			progressDialog.setCancelable(false);
			progressDialog.setTitle("Doing Work");
			progressDialog.setMessage("Retrieving the Number");
			progressDialog.show();
		}

		@Override
		protected void onPostExecute(Double result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			tv1.setText(result + "");
			progressDialog.dismiss();
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			progressDialog.setProgress(Integer.parseInt(values[0].toString()));
		}

	}
}
