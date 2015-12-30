package com.gigabytedx.tutorial.manipulateItems;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.inventory.meta.ItemMeta;

public class AddLandProtectionMetaData {
	public static ItemMeta addLandProtectionMeta(ItemMeta itemMeta) {
		
		itemMeta.setDisplayName(ChatColor.GOLD + "Place this block to claim the land around it");
		List<String> lore = new ArrayList<String>();
		lore.add("Craft your own gold blocks to claim more land");
		lore.add("Right click a player to allow them to build with you");
		lore.add("hint: bury these to make your home look nicer");
		itemMeta.setLore(lore);
		
		return itemMeta;
	}
}
