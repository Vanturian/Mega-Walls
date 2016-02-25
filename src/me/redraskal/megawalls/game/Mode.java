/**
* Mode.java
* 5:19:45 PM
* Created by Redraskal
* All rights reserved.
* Some files may be under a Creative Commons License.
*/
package me.redraskal.megawalls.game;

public enum Mode {

	PRACTICE(0),
	NORMAL(1),
	FACEOFF(2);
	
	public int i;
	
	private Mode(int id) {
		this.i = id;
	}
	
	public int getID() {return this.i; }
}
