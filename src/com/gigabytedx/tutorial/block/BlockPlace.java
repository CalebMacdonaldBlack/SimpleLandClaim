package com.gigabytedx.tutorial.block;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.gigabytedx.tutorial.regions.manipulateRegions;

public class BlockPlace implements Listener{

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event){
		if(event.getBlock().getType().equals(Material.GOLD_BLOCK)){
			manipulateRegions.claimLand(event.getBlock().getLocation(), event.getPlayer());
		}
	}
}
