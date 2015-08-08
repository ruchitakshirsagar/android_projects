package com.example.inclass3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Assignment In Class 3
 * 
 * @author Sanika Joshi Ruchita Kshirsagar 
 * 
 * MainActivity.java
 */
public class MainActivity extends Activity {
	private EditText edt;
	private TextView tv3;
	static String NOTE_VALUE_KEY;
	private Button b1, b2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		b1 = (Button) findViewById(R.id.nextButton);
		b2 = (Button) findViewById(R.id.backButton);

		edt = (EditText) findViewById(R.id.editText1);
		tv3 = (TextView) findViewById(R.id.textView3);

		// Text Change listener
		edt.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (edt.getText().toString() != "") {
					b1.setEnabled(true);
					tv3.setText(edt.getText().toString().length() + "");
				} else {
					edt.setError("Please enter your note here!!");
				}

			}
		});

		b1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String note = edt.getText().toString();
				Intent i = new Intent(MainActivity.this, DisplayActivity.class);
				i.putExtra(NOTE_VALUE_KEY, note);
				startActivity(i);
			}
		});

		// FInish Button
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
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
