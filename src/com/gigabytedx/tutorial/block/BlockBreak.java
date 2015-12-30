package com.gigabytedx.tutorial.block;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import com.gigabytedx.tutorial.manipulateItems.AddLandProtectionMetaData;

public class BlockBreak implements Listener{

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event){
		Player player = event.getPlayer();
		
		if(event.getBlock().getType().equals(Material.GOLD_BLOCK)){
			event.setCancelled(true);
			event.getBlock().setType(Material.AIR);
			ItemStack itemStack = new ItemStack(Material.GOLD_BLOCK);
			itemStack.setItemMeta(AddLandProtectionMetaData.addLandProtectionMeta(itemStack.getItemMeta()));
			event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), itemStack );
		}
		
	}
}
