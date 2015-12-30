package com.gigabytedx.tutorial;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class PrepareItemCraft implements Listener{
	
	@EventHandler
	public void onCraft(PrepareItemCraftEvent event){
		CraftingInventory craftingInventory = event.getInventory();
		if(craftingInventory.getResult().getType().equals(Material.GOLD_BLOCK)){
			ItemMeta itemMeta = craftingInventory.getResult().getItemMeta();
			itemMeta.setDisplayName(ChatColor.GOLD + "Place this block to claim the land around it");
			List<String> lore = new ArrayList<String>();
			lore.add("craft your own gold blocks to claim more land");
			itemMeta.setLore(lore);
			craftingInventory.getResult().setItemMeta(itemMeta);
		}
	}
}
