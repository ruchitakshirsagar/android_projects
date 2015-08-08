package com.example.inclass3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
   Assignment In Class 3
 * @author Sanika Joshi Ruchita Kshirsagar
 * DisplayActivity.java
 */
public class DisplayActivity extends Activity {
	private EditText edt1;
	private Button b1, b2;
	static double number;
	static String note;
	static String NOTE_VALUE_KEY;
	private TextView tv2;

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1) {
			if (resultCode == RESULT_OK) {
				String result = data
						.getStringExtra(CapitalizeActivity.CAPITALIZED_NOTE_KEY);
				tv2.setText(result);
			}
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_displayactivity);

		tv2 = (TextView) findViewById(R.id.textView2);
		b1 = (Button) findViewById(R.id.nextButton);
		b2 = (Button) findViewById(R.id.backButton);
		if (getIntent().getExtras() != null) {
			note = getIntent().getExtras().getString(
					MainActivity.NOTE_VALUE_KEY);
			tv2.setText(note);
		}

		b1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent1 = new Intent(DisplayActivity.this,
						CapitalizeActivity.class);
				intent1.putExtra(NOTE_VALUE_KEY, note);
				// startActivityForResult(intent, requestCode);(intent1);
				startActivityForResult(intent1, 1);
			}
		});

		// Back Button
		b2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity, menu);
		return true;
	}

}
