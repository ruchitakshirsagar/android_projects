package com.example.hw3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

/**
   Assignment HW3
 * @author Sanika Joshi Ruchita Kshirsagar
 * AsynTaskMain.java
 */
public class AsynTaskMain extends AsyncTask<String, Void, ArrayList<String>> {
	ListOfImagesInterface context;
	ProgressDialog progressdialog;
	ArrayList<String> listOfImageUrls;

	AsynTaskMain(ListOfImagesInterface context) {
		this.context = context;
	}

	@Override
	protected ArrayList<String> doInBackground(String... params) {
		listOfImageUrls = new ArrayList<String>();
		// URL will be provided as a parameter
		try {
			URL url = new URL(params[0]);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			Log.d("Connection String is", params[0]);
			con.connect();
			int statusCode = con.getResponseCode();
			if (statusCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
				String line;
				while ((line = in.readLine()) != null) {
					listOfImageUrls.add(line.trim());
				}
				in.close();
				con.disconnect();
			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listOfImageUrls;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		progressdialog = new ProgressDialog(context.getContext());
		progressdialog.setMessage("Retrieving Image URLs...");
		progressdialog.setCancelable(false);
		progressdialog.setIndeterminate(true);
		progressdialog.show();
	}

	@Override
	protected void onPostExecute(ArrayList<String> result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		if (progressdialog != null) {
			context.setImageList(listOfImageUrls);
			progressdialog.dismiss();
		}
	}

	@Override
	protected void onProgressUpdate(Void... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
	}

	static public interface ListOfImagesInterface {
		public MainActivity getContext();

		public void setImageList(ArrayList<String> listOfImages);
	}
}
