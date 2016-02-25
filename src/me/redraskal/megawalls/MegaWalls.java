/**
* MegaWalls.java
* 4:21:15 PM
* Created by Redraskal
* All rights reserved.
* Some files may be under a Creative Commons License.
*/
package me.redraskal.megawalls;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MegaWalls extends JavaPlugin {

	public Server SERVER = getServer();
	public PluginManager PLUGIN_MANAGER = SERVER.getPluginManager();
	
	private static MegaWalls INSTANCE = null;
	public static MegaWalls getInstance() {return INSTANCE; }
	
	public static String colorize(String message) {return ChatColor.translateAlternateColorCodes('&', message); }
	
	public static List<String> colorizeList(List<String> msg) {
		List<String> newmsg = new ArrayList<String>();
		
		for(String s : msg) {
			newmsg.add(colorize(s));
		}
		
		return newmsg;
	}
	
	public void onEnable() {
		INSTANCE = this;
		
		//TODO
	}
	
	public void onDisable() {
		//TODO
	}
}
