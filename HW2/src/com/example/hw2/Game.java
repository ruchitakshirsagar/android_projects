/**
 * 
 */
package com.example.hw2;

/**
 * Assignment HW1
 * 
 * @author Sanika Joshi Ruchita Kshirsagar Game.java
 */
public class Game {

	String current_player;
	static String State;

	public String getCurrent_player() {
		return current_player;
	}

	public void setCurrent_player(String current_player) {
		synchronized (this) {
			this.current_player = current_player;
		}
	}

	public static String getState() {
		return State;
	}

	public static void setState(String state) {
		State = state;
	}

}
