package com.gigabytedx.tutorial.manipulateItems;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.inventory.meta.ItemMeta;

public class AddLandProtectionMetaData {
	public static ItemMeta addLandProtectionMeta(ItemMeta itemMeta) {
		
		itemMeta.setDisplayName(ChatColor.GOLD + "Place this block to claim the land around it");
		List<String> lore = new ArrayList<String>();
		lore.add("craft your own gold blocks to claim more land");
		itemMeta.setLore(lore);
		
		return itemMeta;
	}
}
