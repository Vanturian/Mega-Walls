package me.redraskal.megawalls.utils;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import me.redraskal.megawalls.MegaWalls;

public class SkullCreator {
	
	public static ItemStack createSkull(String owner, int amount, String name, List<String> lore) {
		ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		
		item.setAmount(amount);
		
		meta.setOwner(owner);
		
		meta.setDisplayName(MegaWalls.colorize(name));
		meta.setLore(MegaWalls.colorizeList(lore));
		
		item.setItemMeta(meta);
		
		return item;
	}
	
	public static ItemStack createSkull(String owner, int amount, String name) {
		ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		
		item.setAmount(amount);
		
		meta.setOwner(owner);
		
		meta.setDisplayName(MegaWalls.colorize(name));
		
		item.setItemMeta(meta);
		
		return item;
	}
}
