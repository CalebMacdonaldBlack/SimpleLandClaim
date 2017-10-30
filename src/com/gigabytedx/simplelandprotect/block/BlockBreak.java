package com.gigabytedx.simplelandprotect.block;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import com.gigabytedx.simplelandprotect.Main;
import com.gigabytedx.simplelandprotect.manipulateItems.AddLandProtectionMetaData;
import com.gigabytedx.simplelandprotect.regions.manipulateRegions;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

public class BlockBreak implements Listener{

	@EventHandler 
	public void onBlockBreak(BlockBreakEvent event){
		if(event.getPlayer().hasPermission("simplelandprotection.removeanylandblock") && event.getBlock().getType().equals(Material.GOLD_BLOCK)) {
			manipulateRegions.removeRegion(event.getPlayer(), event.getBlock().getLocation());
		}else if(getWorldGuard().canBuild(event.getPlayer(), event.getBlock()))
			if(event.getBlock().getType().equals(Material.GOLD_BLOCK)){
				if(getWorldGuard().getRegionManager(event.getPlayer().getWorld()).getRegion(getLocationNameForRegionId(event.getBlock().getLocation())).getOwners().contains(event.getPlayer().getUniqueId())){
					event.setCancelled(true);
					event.getBlock().setType(Material.AIR);
					ItemStack itemStack = new ItemStack(Material.GOLD_BLOCK);
					itemStack.setItemMeta(AddLandProtectionMetaData.addLandProtectionMeta(itemStack.getItemMeta()));
					event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), itemStack );
					
					manipulateRegions.removeRegion(event.getPlayer(), event.getBlock().getLocation());
				}else{
					event.getPlayer().sendMessage(ChatColor.RED + "This doesn't belong to you!");
					event.setCancelled(true);
				}
			}
		
	}
	
	private static WorldGuardPlugin getWorldGuard() {
	    Plugin plugin = Main.pluginInstance.getServer().getPluginManager().getPlugin("WorldGuard");
	 
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
