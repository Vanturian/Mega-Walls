/**
* Team.java
* 5:09:02 PM
* Created by Redraskal
* All rights reserved.
* Some files may be under a Creative Commons License.
*/
package me.redraskal.megawalls.game;

import org.bukkit.ChatColor;

public enum Team {

	YELLOW(ChatColor.YELLOW),
	GREEN(ChatColor.GREEN),
	RED(ChatColor.RED),
	BLUE(ChatColor.BLUE);
	
	public ChatColor c;
	
	private Team(ChatColor color) {
		this.c = color;
	}
	
	public ChatColor getColor() {return this.c; }
}
