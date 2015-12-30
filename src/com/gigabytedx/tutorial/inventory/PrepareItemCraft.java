package com.gigabytedx.tutorial.inventory;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;

import com.gigabytedx.tutorial.manipulateItems.AddLandProtectionMetaData;

public class PrepareItemCraft implements Listener {

	@EventHandler
	public void onCraft(PrepareItemCraftEvent event) {
		CraftingInventory craftingInventory = event.getInventory();
		if (craftingInventory.getResult().getType().equals(Material.GOLD_BLOCK)) {
			craftingInventory.getResult().setItemMeta(
					AddLandProtectionMetaData.addLandProtectionMeta(craftingInventory.getResult().getItemMeta()));
		}
	}
}
