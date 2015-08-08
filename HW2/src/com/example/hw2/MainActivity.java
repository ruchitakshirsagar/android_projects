package com.example.hw2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	TextView tv1;
	Button exitButton,creditButton;
	Button resetButton;
	ImageView[][] imgVArray = new ImageView[3][3];
	static Game g;
	ImageView imgV;
	final int array_length = 3;
	static int game_count = 1;
	AlertDialog.Builder builder;
	private final int[] myImageViewIds = { R.id.imageView7, R.id.imageView8,
			R.id.imageView9, R.id.imageView4, R.id.imageView5, R.id.imageView6,
			R.id.imageView1, R.id.imageView2, R.id.imageView3 };

	public void createDialog(String gameState) {
		// 1. Instantiate an AlertDialog.Builder with its constructor
		// 2. Chain together various setter methods to set the dialog
		// characteristics
		switch (gameState) {
		case "Draw":
			builder.setMessage(R.string.dialogMessageDraw).setTitle(
					R.string.dialog_title);
			break;
		case "Win1":
			builder.setMessage(R.string.dialogMessagePlayer1W).setTitle(
					R.string.dialog_title);
			break;
		case "Win2":
			builder.setMessage(R.string.dialogMessagePlayer2W).setTitle(
					R.string.dialog_title);
			break;

		}

		AlertDialog dialog = builder.create();
		dialog.show();
	}

	public boolean checkRow(int row_id, String player_Name) {
		int count = 0;
		for (int j = 0; j < array_length; j++) {
			if (imgVArray[row_id][j].getTag(R.id.tag_id_player).equals(
					player_Name)) {
				count++;
			}
		}

		return ((count == 3) ? true : false);
	}

	public boolean checkColumn(int column_id, String player_Name) {
		int count = 0;
		for (int i = 0; i < array_length; i++) {
			if (imgVArray[i][column_id].getTag(R.id.tag_id_player).equals(
					player_Name)) {
				count++;
			}
		}
		return ((count == 3) ? true : false);
	}

	public boolean checkDiagonal(int rowId, int columnId, String player_Name) {
		int count = 0;

		/*if ((rowId == columnId) && (rowId != array_length - 1 || rowId != 0)) {
			for (int j = 0; j > 2; j++) {
				if (imgVArray[j][j].getTag(R.id.tag_id_player).equals(
						player_Name)) {
					count++;
				}
			}
			if (count != 3) {
				count = 0;
				for (int j = 0; j < 3; j++) {
						if (imgVArray[j][j].getTag(R.id.tag_id_player).equals(
								player_Name)) {
							count++;
						}
					}
				}
			}*/
		 /* Condition for either 00 or 22 */
		if (rowId == columnId && (rowId == array_length - 1 || rowId == 0)) {
			count = 0;
			for (int j = 0; j  < 3; j++) {
				if (imgVArray[j][j].getTag(R.id.tag_id_player).equals(
						player_Name)) {
					count++;
				}
			}

		} else if ((rowId == array_length - 1 && columnId == 0) || (rowId == 0
				&& columnId == array_length - 1)) {
			count = 0;
			int i = (array_length - 1);
			// condition for diagonal from 2 to 0
			for (int j = 0; j < 3; j++) {
				if (imgVArray[i][j].getTag(R.id.tag_id_player).equals(
						player_Name)) {
					i--;
					count++;
				}

			}
		}

		return ((count == 3) ? true : false);
	}

	public boolean checkGameWinOrLoose(ImageView imgView) {
		// Check rows
		// Check columns
		// Check diagonals
		int rowId = 0, columnId = 0;

		int idToFind = imgView.getId();
		boolean found = false;
		String tag = imgView.getTag(R.id.tag_id_player).toString();

		for (int i = 0; i < 3; i++) {
			if (!found) {
				for (int j = 0; j < 3; j++) {
					if (imgVArray[i][j].getId() == idToFind) {
						rowId = i;
						columnId = j;
						found = true;
					}
				}
			}
		}

		if (found == true) {
			if (checkRow(rowId, tag) || checkColumn(columnId, tag)
					|| checkDiagonal(rowId, columnId, tag)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	class ImageViewListener implements View.OnClickListener {

		public void switchPlayer() {
			if (g.current_player.equals("Player1")) {
				g.setCurrent_player("Player2");
			} else if (g.current_player.equals("Player2")) {
				g.setCurrent_player("Player1");
			}
		}

		
		@Override
		public void onClick(View v) {
			String current_player = g.getCurrent_player();
			imgV = (ImageView) findViewById(v.getId());
			boolean winOrLoose;
			Intent scoreBoardIntent = new Intent(MainActivity.this,ScoreBoardActivity.class);
			
			switch (current_player) {
			case "Player1":
				// TODO Check if the imageView is blank
				String tag = imgV.getTag(R.id.tag_id_player).toString();
				if (imgV.getTag(R.id.tag_id_player).toString().equals("None")) {
					imgV.setImageResource(R.drawable.lettero);
					imgV.setTag(R.id.tag_id_player, "Player1");
					// Count of how many squares are checked
					game_count++;
					
					switchPlayer();
					winOrLoose = checkGameWinOrLoose(imgV);

					if (!winOrLoose && game_count == 10) {
						scoreBoardIntent.putExtra("DRAW_COUNT", 1);
						startActivity(scoreBoardIntent);
					} else if (!winOrLoose && game_count < 10)
						tv1.setText("Player 2's Turn");
					    //scoreBoardIntent.putExtra();
					else if (winOrLoose) {
						tv1.setText("Player 1 wins !!");
						createDialog("Win1");
						//saveSharedPreferences("WINNER_PLAYER_1",1);
						scoreBoardIntent.putExtra("Player_1_Won_Count",1);
						startActivity(scoreBoardIntent);
					} 
				} else {
					Toast.makeText(MainActivity.this,
							"Please select another square", 20);
				}
				break;
			case "Player2":
				if (imgV.getTag(R.id.tag_id_player).equals("None")) {

					imgV.setImageResource(R.drawable.letterx);
					imgV.setTag(R.id.tag_id_player, "Player2");
					// Count of how many squares are checked
					game_count++;
					Toast.makeText(MainActivity.this, game_count + "", 30)
							.show();
					switchPlayer();
					winOrLoose = checkGameWinOrLoose(imgV);
					if (!winOrLoose && game_count == 10) {
						//createDialog("Draw");
						scoreBoardIntent.putExtra("DRAW_COUNT", 1);
						startActivity(scoreBoardIntent);
					} else if (!winOrLoose && game_count < 11)
						tv1.setText("Player 1's Turn");
					else if (winOrLoose) {
						tv1.setText("Player 2 wins");
						scoreBoardIntent.putExtra("Player_2_Won_Count", 1);
						//saveSharedPreferences("WINNER_PLAYER_2",1);
						startActivity(scoreBoardIntent);
					} 
				} else {
					Toast.makeText(MainActivity.this,
							"Please select another square", 20);
				}
				break;
			}

		}
	}

	public void initialiseBoard() {
		int rowId = 0;
		game_count =1;
		for (int i = 0; i < 9; i++) {
			((ImageView) findViewById(myImageViewIds[i]))
					.setImageResource(R.drawable.empty);

		}

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				imgVArray[i][j] = (ImageView) findViewById(myImageViewIds[rowId]);
				imgVArray[i][j].setTag(R.id.tag_id_player, "None");
				imgVArray[i][j].setOnClickListener(new ImageViewListener());
				rowId++;
			}
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		int rowId = 0;
		game_count=1;
		tv1 = (TextView) findViewById(R.id.textView1);
		// Set the background color of the text view to light blue
		tv1.setBackgroundColor(getResources().getColor(R.color.lightBlue));
		tv1.setText("Player 1's Turn");
		// Start the game with Player 1
		g = new Game();
		g.setCurrent_player("Player1");

		builder = new AlertDialog.Builder(MainActivity.this);
		builder.setPositiveButton(R.string.ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// Use dismiss when you want the dialog to be dismissed
						// when its job is finished and it is being removed from
						// the screen

						dialog.dismiss();
					}
				});

		exitButton = (Button) findViewById(R.id.button4);
		resetButton = (Button) findViewById(R.id.button2);
        creditButton = (Button) findViewById(R.id.button3); 
		/*
		 * final int[] myImageViewIds = { R.id.imageView1, R.id.imageView2,
		 * R.id.imageView3, R.id.imageView4, R.id.imageView5, R.id.imageView6,
		 * R.id.imageView7, R.id.imageView8, R.id.imageView9 };
		 */
		/*
		 * for (int i = 0; i < 3; i++) { for (int j = 0; j < 3; j++) {
		 * imgVArray[i][j] = (ImageView) findViewById(myImageViewIds[rowId]);
		 * imgVArray[i][j].setTag(R.id.tag_id_player, "None");
		 * imgVArray[i][j].setOnClickListener(new ImageViewListener()); rowId++;
		 * } }
		 */

		initialiseBoard();
		resetButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				initialiseBoard();
			}
		});

		exitButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		creditButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent("com.example.hw2.intent.action.VIEW");
				intent.addCategory(Intent.CATEGORY_DEFAULT);
				startActivity(intent);
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
