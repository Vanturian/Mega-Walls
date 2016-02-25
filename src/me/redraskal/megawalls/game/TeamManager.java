/**
* TeamManager.java
* 5:10:45 PM
* Created by Redraskal
* All rights reserved.
* Some files may be under a Creative Commons License.
*/
package me.redraskal.megawalls.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.entity.Player;

public class TeamManager {

	public static Map<Team, ArrayList<Player>> players = new HashMap<Team, ArrayList<Player>>();
	
	public static boolean inTeam(Player player) {
		boolean found = false;
		
		for(ArrayList<Player> pl : players.values()) {
			if(pl.contains(player)) {
				found = true;
			}
		}
		
		return found;
	}
	
	public static Team getTeam(Player player) {
		Team team = null;
		
		for(Entry<Team, ArrayList<Player>> s : players.entrySet()) {
			if(s.getValue().contains(player)) {
				team = s.getKey();
			}
		}
		
		return team;
	}
	
	public static void setTeam(Player player, Team team) {
		for(Entry<Team, ArrayList<Player>> s : players.entrySet()) {
			if(s.getValue().contains(player)) {
				if(s.getKey() == team) {
					s.getValue().add(player);
				} else {
					s.getValue().remove(player);
				}
			}
		}
	}
	
	public static void removePlayer(Player player) {
		for(Entry<Team, ArrayList<Player>> s : players.entrySet()) {
			if(s.getValue().contains(player)) {
				s.getValue().remove(player);
			}
		}
	}
}
