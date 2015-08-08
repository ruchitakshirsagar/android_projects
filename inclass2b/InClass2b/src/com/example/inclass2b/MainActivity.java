package com.example.inclass2b;
/* Assignment 2
 * MainActivity.java
 * Ruchita Khirsagar Sanika Joshi
 * */

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends Activity implements OnClickListener {
	private LinearLayout root;
	private RadioButton b1;
	private RadioButton b2;
	private RadioButton b3;
	private RadioButton b4;
	private RadioButton b5;
	private SeekBar seekBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		root = (LinearLayout) findViewById(R.id.LinearLayout1);
		root.setBackgroundColor(Color.WHITE);
         //RadioGroup rg = (RadioGroup) findViewById(R.id.);
		 b1 = (RadioButton) findViewById(R.id.rbBlack);
		 b2 = (RadioButton) findViewById(R.id.rbWhite);
		 b3 = (RadioButton) findViewById(R.id.rbRed);
		 b4 = (RadioButton) findViewById(R.id.rbGreen);
		 b5 = (RadioButton) findViewById(R.id.rbBlue);
		
		b1.setOnClickListener(this);
		b2.setOnClickListener(this);
		b3.setOnClickListener(this);
		b4.setOnClickListener(this);
		b5.setOnClickListener(this);
		
		b2.setChecked(true);
		
		b1.setTag(R.id.tag_key_color, Color.BLACK);
		b2.setTag(R.id.tag_key_color, Color.WHITE);
		b3.setTag(R.id.tag_key_color, Color.RED);
		b4.setTag(R.id.tag_key_color, Color.GREEN);
		b5.setTag(R.id.tag_key_color, Color.BLUE);
		
		seekBar = (SeekBar) findViewById(R.id.seekBarColor);
	    //seekBar.setProgress(255);
	    seekBar.setMax(255);
		seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
    
			@SuppressLint("NewApi")
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				int y = progress;
				root.getBackground().setAlpha(y);
				
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
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
		// TODO Auto-generated method stub
		int color = ((Integer) (v.getTag(R.id.tag_key_color))).intValue();
		//root.setBackgroundColor(color);
        root.setBackgroundColor(color);
        int progressVal = seekBar.getProgress();
		root.getBackground().setAlpha(seekBar.getProgress());
	}

	

}
