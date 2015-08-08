package com.example.inclass3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


/**
   Assignment In Class 3
 * @author Sanika Joshi Ruchita Kshirsagar
 * CapitalizeActivity.java
 */
public class CapitalizeActivity extends Activity {

	private Button b1;
    private EditText edt1;
    private static String note;
    private static String capitalizedNote;
	 static String CAPITALIZED_NOTE_KEY;
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_capitalizeactivity);

		b1 = (Button) findViewById(R.id.backButton);
		edt1 = (EditText) findViewById(R.id.editText1);
		
		if (getIntent().getExtras() != null) {
			note = getIntent().getExtras().getString(DisplayActivity.NOTE_VALUE_KEY);
		    
		}
		
		b1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				capitalizedNote = note.toUpperCase();
				Intent intent3 = new Intent(CapitalizeActivity.this,DisplayActivity.class);
				intent3.putExtra(CAPITALIZED_NOTE_KEY, capitalizedNote);
				setResult(RESULT_OK, intent3);
				finish();
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_b, menu);
		return true;
	}

}
