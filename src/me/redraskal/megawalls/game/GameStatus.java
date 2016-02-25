/**
* GameStatus.java
* 5:17:11 PM
* Created by Redraskal
* All rights reserved.
* Some files may be under a Creative Commons License.
*/
package me.redraskal.megawalls.game;

public enum GameStatus {

	WAITING(0),
	STARTING(1),
	INGAME(2),
	FINISHED(3);
	
	public int i;
	
	private GameStatus(int id) {
		this.i = id;
	}
	
	public int getID() {return this.i; }
}
