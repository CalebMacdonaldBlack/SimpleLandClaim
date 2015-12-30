package com.gigabytedx.tutorial.block;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.Plugin;

import com.gigabytedx.tutorial.Tutorial;
import com.gigabytedx.tutorial.regions.manipulateRegions;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

public class BlockPlace implements Listener{

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event){
		if(getWorldGuard().canBuild(event.getPlayer(), event.getBlock()))
			if(event.getBlock().getType().equals(Material.GOLD_BLOCK)){
				manipulateRegions.claimLand(event.getBlock().getLocation(), event.getPlayer());
			}
	}
	
	private static WorldGuardPlugin getWorldGuard() {
	    Plugin plugin = Tutorial.pluginInstance.getServer().getPluginManager().getPlugin("WorldGuard");
	 
	    // WorldGuard may not be loaded
	    if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
	        return null; // Maybe you want throw an exception instead
	    }
	 
	    return (WorldGuardPlugin) plugin;
	}
}
