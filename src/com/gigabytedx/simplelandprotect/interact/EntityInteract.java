package com.gigabytedx.simplelandprotect.interact;

import com.gigabytedx.simplelandprotect.Main;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.domains.DefaultDomain;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.plugin.Plugin;

public class EntityInteract implements Listener {

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onInteract(PlayerInteractEntityEvent event) {
        EquipmentSlot hand = event.getHand();
        if (hand.equals(EquipmentSlot.HAND)) {
            if (event.getRightClicked() instanceof Player
                    && event.getPlayer().getItemInHand().getType().equals(Material.GOLD_BLOCK)) {
                Player friend = (Player) event.getRightClicked();
                String canBuild = "notSet";
                for (World world : Bukkit.getWorlds())
                    for (ProtectedRegion region : getWorldGuard().getRegionManager(world).getRegions().values()) {
                        if (region.getOwners().contains(event.getPlayer().getUniqueId())) {
                            DefaultDomain members = region.getMembers();

                            if ((members.contains(friend.getUniqueId()) && !(canBuild.equals("yes"))) || canBuild.equals("no")) {
                                members.removePlayer(friend.getUniqueId());
                                region.setMembers(members);
                                canBuild = "no";
                            } else {
                                members.addPlayer(friend.getUniqueId());
                                region.setMembers(members);
                                canBuild = "yes";
                            }
                        }
                    }

                if (canBuild.equals("yes")) {
                    event.getPlayer().sendMessage(ChatColor.GREEN + Main.pluginInstance.getName() + " - "
                            + ChatColor.GOLD + "You have allowed " + friend.getName() + " to build on your land");
                    friend.sendMessage(ChatColor.GREEN + Main.pluginInstance.getName() + " - " + ChatColor.GOLD
                            + event.getPlayer().getName() + " has allowed you to build on his land");
                } else {
                    event.getPlayer().sendMessage(ChatColor.GREEN + Main.pluginInstance.getName() + " - "
                            + ChatColor.GOLD + friend.getName() + " can no longer build on your land");
                    friend.sendMessage(ChatColor.GREEN + Main.pluginInstance.getName() + " - " + ChatColor.RED
                            + "you can no longer build on " + event.getPlayer().getName() + "'s land");
                }
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
}
