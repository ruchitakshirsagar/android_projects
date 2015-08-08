package com.example.hw2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class ScoreBoardActivity extends Activity {
	TextView tv1, tv2, tv7, tv8;
	Button b1;
    ProgressDialog progressBar;
    ProgressBar pb1,pb2;
    private int progressBarStatus = 0,progressBarStatus2 = 0;
    private Handler progressBarHandler = new Handler();
    private Handler progressBarHandler2 = new Handler();
    
    private int percentWonByOne,percentWonByTwo;
    
	public int saveSharedPreferences(String key, int value) {
		SharedPreferences preferences = getPreferences(MODE_PRIVATE);

		SharedPreferences.Editor editor = preferences.edit();
		int some_Score = preferences.getInt(key, 0);

		some_Score = some_Score + value;
		editor.putInt(key, some_Score);
		editor.commit();
		return some_Score;
	
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score_board);
		// SharedPreferences sharedPreferences =
		// PreferenceManager.getDefaultSharedPreferences(MODE_PRIVATE);
		tv1 = (TextView) findViewById(R.id.textView5);
		tv2 = (TextView) findViewById(R.id.textView6);
	
		tv7 = (TextView) findViewById(R.id.textView7);
		tv8 = (TextView) findViewById(R.id.textView8);
		pb1=  (ProgressBar) findViewById(R.id.progressBar1);
		pb1.setProgress(0);
		pb1.setMax(100);
		//pb1.setVisibility(1);
		pb2= (ProgressBar) findViewById(R.id.progressBar2);
		pb2.setProgress(0);
		pb2.setMax(100);
		
		/*progressBar = new ProgressDialog(this);
		progressBar.setCancelable(true);
		progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressBar.setProgress(0);
		progressBar.setMax(100);
		progressBar.show();
		 */
		// thread implementation started
		 
		
		
		//thread implementation ended
		
		b1 = (Button) findViewById(R.id.button1);

		 if (getIntent().getIntExtra("Player_1_Won_Count",0) != 0) {
		 int count1 = getIntent().getIntExtra("Player_1_Won_Count",0);
		 tv1.setText(Integer.toString(saveSharedPreferences("WINNER_PLAYER_1",count1)));
		 tv7.setText("Congratulations to Player 1 for winning the last game");
		 }
		 
		 if(getIntent().getIntExtra("Player_2_Won_Count",0) != 0) {
		 int count2 = getIntent().getIntExtra("Player_2_Won_Count",0);
		 tv2.setText(Integer.toString(saveSharedPreferences("WINNER_PLAYER_2",count2)));
		 tv7.setText("Congratulations to Player 2 for winning the last game");
		 }
		 
		 int drawCount = getIntent().getIntExtra("Draw_Count",0);
		 saveSharedPreferences("Draw_Count", 1);
		 
		 int playerOneCount = getPreferences(MODE_PRIVATE).getInt("WINNER_PLAYER_1", 0);
		 int playerTwoCount = getPreferences(MODE_PRIVATE).getInt("WINNER_PLAYER_2", 0);
		 int totalGames = playerOneCount + playerTwoCount  + drawCount;
		 tv8.setText(Integer.toString(totalGames));
		 
		 if(totalGames != 0) {
		     double total = (double)playerOneCount/(double)totalGames;
			 double total2 = (double)playerTwoCount/(double)totalGames;
			 
		     percentWonByOne = (int) (total * 100)  ;
		     tv2.setText(Integer.toString(percentWonByOne) + "%");         
				
			 percentWonByTwo = (int) (total2 * 100);
			 tv1.setText(Integer.toString(percentWonByTwo)+ "%");         
			  
		 }
		 
		 b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		 
		 new Thread(new Runnable() {
			  public void run() {
				while (progressBarStatus < 100) {

				  // process some tasks
				  progressBarStatus = (int) percentWonByOne  ;
                  // your computer is too fast, sleep 1 second
				  try {
					Thread.sleep(1000);
				  } catch (InterruptedException e) {
					e.printStackTrace();
				  }

				  // Update the progress bar
				  progressBarHandler.post(new Runnable() {
					public void run() {
					  pb1.setProgress(progressBarStatus);
					}
				  });
				}

				// ok, file is downloaded,
				if (progressBarStatus >= 100) {

					// sleep 2 seconds, so that you can see the 100%
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					
				}
			  }
		       }).start();


	// Thread for progressbar two
		 
		 new Thread(new Runnable() {
			  public void run() {
				while (progressBarStatus2 < 100) {

				  // process some tasks
				  progressBarStatus2 = (int) percentWonByTwo  ;
				  
				  // your computer is too fast, sleep 1 second
				  try {
					Thread.sleep(1000);
				  } catch (InterruptedException e) {
					e.printStackTrace();
				  }

				  // Update the progress bar
				  progressBarHandler2.post(new Runnable() {
					public void run() {
					  pb2.setProgress(progressBarStatus2);
					}
				  });
				}

				// ok, file is downloaded,
				if (progressBarStatus2 >= 100) {

					// sleep 2 seconds, so that you can see the 100%
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					
				}
			  }
		       }).start();


	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.score_board, menu);
		return true;
	}

}
