/**
* MegaWalls.java
* 4:21:15 PM
* Created by Redraskal
* All rights reserved.
* Some files may be under a Creative Commons License.
*/
package me.redraskal.megawalls;

import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MegaWalls extends JavaPlugin {

	public Server SERVER = getServer();
	public PluginManager PLUGIN_MANAGER = SERVER.getPluginManager();
	
	private static MegaWalls INSTANCE = null;
	public static MegaWalls getInstance() {return INSTANCE; }
	
	public void onEnable() {
		INSTANCE = this;
		
		//TODO
	}
	
	public void onDisable() {
		//TODO
	}
}
