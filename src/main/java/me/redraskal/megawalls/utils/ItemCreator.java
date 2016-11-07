package me.redraskal.megawalls.utils;

import java.util.List;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;

import me.redraskal.megawalls.MegaWalls;

public class ItemCreator {

	public static ItemStack createItem(Material type, int amount, int data, String name, List<String> lore) {
		ItemStack item = new ItemStack(type, 1, (short) data);
		ItemMeta meta = item.getItemMeta();
		
		item.setAmount(amount);
		
		meta.setDisplayName(MegaWalls.colorize(name));
		meta.setLore(MegaWalls.colorizeList(lore));
		
		item.setItemMeta(meta);
		
		return item;
	}
	
	public static ItemStack createItemPotion(Material type, int amount, int data, String name, List<String> lore) {
		ItemStack item = new ItemStack(type, 1, (short) data);
		PotionMeta meta = (PotionMeta) item.getItemMeta();
		
		meta.clearCustomEffects();
		
		item.setAmount(amount);
		
		meta.setDisplayName(MegaWalls.colorize(name));
		meta.setLore(MegaWalls.colorizeList(lore));
		
		item.setItemMeta(meta);
		
		return item;
	}
	
	public static ItemStack createItem(Material type, int amount, int data, String name) {
		ItemStack item = new ItemStack(type, 1, (short) data);
		ItemMeta meta = item.getItemMeta();
		
		item.setAmount(amount);
		
		meta.setDisplayName(MegaWalls.colorize(name));
		
		item.setItemMeta(meta);
		
		return item;
	}
	
	public static ItemStack createArmour(Material type, int amount, Color color, String name) {
		ItemStack item = new ItemStack(type, 1, (short) 0);
		LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
		
		item.setAmount(amount);
		
		meta.setDisplayName(MegaWalls.colorize(name));
		
		meta.setColor(color);
		
		item.setItemMeta(meta);
		
		return item;
	}
	
	public static ItemStack createArmour(Material type, int amount, Color color, String name, List<String> lore) {
		ItemStack item = new ItemStack(type, 1, (short) 0);
		LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
		
		item.setAmount(amount);
		
		meta.setDisplayName(MegaWalls.colorize(name));
		
		meta.setLore(MegaWalls.colorizeList(lore));
		
		meta.setColor(color);
		
		item.setItemMeta(meta);
		
		return item;
	}
}
