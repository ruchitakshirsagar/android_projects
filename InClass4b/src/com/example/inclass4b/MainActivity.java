package com.example.inclass4b;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	Button b1;
	ExecutorService threadPool;
	ProgressDialog progressDialog;
	Handler handler;
	TextView tv1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		b1 = (Button) findViewById(R.id.button1);
	
		tv1 = (TextView) findViewById(R.id.textView1);
		
		threadPool = Executors.newFixedThreadPool(4); 	
		handler = new Handler(new Handler.Callback(){

			@Override
			public boolean handleMessage(Message msg) {
				// TODO Auto-generated method stub
				return false;
			}
			
		});
		
		b1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				threadPool.execute(new HeavyWork());
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	class HeavyWork implements Runnable  {

		static final int STATUS_START = 0x00;
   		static final int STATUS_STOP = 0x02;
   		static final int STATUS_STEP = 0X01;
   		
		public double getNumber() {
			double value = 0.0d;
			value = (double) Math.random();
			return value;
		}
		
		@Override
		public void run() {
	   		Message msg = new Message();
	   		msg.what=STATUS_START;
	   		handler.sendMessage(msg);
	   		
			for(int i = 0 ; i < 10000 ; i++) {
	   			for(int j =0 ;j < 10000; j++) {
	   				
	   			}
	   			msg = new Message();
   				msg.what=STATUS_STEP;
   				msg.obj = i+1;
   		   		handler.sendMessage(msg);
	   		}
			//Calculated the number
			tv1.setText(getNumber() + "");
			msg = new Message();
			msg.what=STATUS_STOP;
	   		handler.sendMessage(msg);
		}
		
	}
}
