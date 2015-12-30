package com.gigabytedx.tutorial.regions;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.gigabytedx.tutorial.Tutorial;
import com.sk89q.worldedit.BlockVector;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.domains.DefaultDomain;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;

public class manipulateRegions {

	public static void claimLand(Location location, Player player) {
		BlockVector vector = new BlockVector(7, 7, 7);
		BlockVector vectorA = new BlockVector(location.getBlockX(), location.getBlockY(), location.getBlockZ());
		BlockVector vectorB = vectorA;
		
		vectorA = new BlockVector(vectorA.subtract(vector));
		vectorB = new BlockVector(vectorB.add(vector));
		ProtectedCuboidRegion region = new ProtectedCuboidRegion(getLocationNameForRegionId(location), vectorA, vectorB );
		
		DefaultDomain owners = new DefaultDomain();
		owners.addPlayer(player.getUniqueId());
		region.setOwners(owners);
		
		region.setFlag(DefaultFlag.GREET_MESSAGE, "You have entered " + player.getName() + "'s property");
		region.setFlag(DefaultFlag.FAREWELL_MESSAGE, "You have left " + player.getName() + "'s property");
		
		getWorldGuard().getRegionManager(location.getWorld()).addRegion(region);
		player.sendMessage(ChatColor.GREEN + Tutorial.pluginInstance.getName() + " - " + ChatColor.GOLD + "You have successfully added this region");
	}
	
	private static WorldGuardPlugin getWorldGuard() {
	    Plugin plugin = Tutorial.pluginInstance.getServer().getPluginManager().getPlugin("WorldGuard");
	 
	    // WorldGuard may not be loaded
	    if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
	        return null; // Maybe you want throw an exception instead
	    }
	 
	    return (WorldGuardPlugin) plugin;
	}
	
	private static String getLocationNameForRegionId(Location location){
		String regionName =  String.valueOf(location.getX()) + String.valueOf(location.getY()) + String.valueOf(location.getZ()) + location.getWorld().getName();
		regionName = regionName.replace(".", "");
		return regionName;
	}
}
