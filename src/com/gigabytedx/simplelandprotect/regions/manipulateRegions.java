package com.gigabytedx.simplelandprotect.regions;

import com.gigabytedx.simplelandprotect.Main;
import com.sk89q.worldedit.BlockVector;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.domains.DefaultDomain;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class manipulateRegions {

    public static boolean claimLand(Location location, Player player) {
        int dist = Main.pluginInstance.getConfig().getInt("cuboid size");
        BlockVector vector = new BlockVector(dist, dist, dist);
        BlockVector vectorA = new BlockVector(location.getBlockX(), location.getBlockY(), location.getBlockZ());
        BlockVector vectorB = vectorA;

        vectorA = new BlockVector(vectorA.subtract(vector));
        vectorB = new BlockVector(vectorB.add(vector));
        ProtectedCuboidRegion region = new ProtectedCuboidRegion(getLocationNameForRegionId(location), vectorA, vectorB);
        List<ProtectedRegion> list = region.getIntersectingRegions(getWorldGuard().getRegionManager(location.getWorld()).getRegions().values());

        for (ProtectedRegion element : list) {
            if (!(element.getOwners().contains(player.getUniqueId())))
                return false;
        }


        DefaultDomain owners = new DefaultDomain();
        owners.addPlayer(player.getUniqueId());
        region.setOwners(owners);

        region.setFlag(DefaultFlag.GREET_MESSAGE, "You have entered " + player.getName() + "'s property");
        region.setFlag(DefaultFlag.FAREWELL_MESSAGE, "You have left " + player.getName() + "'s property");

        getWorldGuard().getRegionManager(location.getWorld()).addRegion(region);
        player.sendMessage(ChatColor.GREEN + Main.pluginInstance.getName() + " - " + ChatColor.GOLD + "You have successfully added this region");


        List<ProtectedRegion> playerOwnedRegions = Main.pluginInstance.getServer().getWorlds().stream()
                .map(w -> getWorldGuard().getRegionManager(w).getRegions().values())
                .flatMap(Collection::stream)
                .collect(Collectors.toList())
                .stream()
                .filter(r -> r.isOwner(getWorldGuard().wrapPlayer(player)))
                .collect(Collectors.toList());

        playerOwnedRegions
                .stream()
                .map(r2 -> region.getMembers().getUniqueIds())
                .flatMap(Collection::stream)
                .collect(Collectors.toSet())
                .stream()
                .map(Bukkit::getPlayer)
                .collect(Collectors.toList())
                .forEach(p -> {
                    playerOwnedRegions.forEach(por -> {
                        por.getMembers().addPlayer(p.getUniqueId());
                    });
                });
        return true;
    }

    private static WorldGuardPlugin getWorldGuard() {
        Plugin plugin = Main.pluginInstance.getServer().getPluginManager().getPlugin("WorldGuard");

        // WorldGuard may not be loaded
        if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
            return null; // Maybe you want throw an exception instead
        }

        return (WorldGuardPlugin) plugin;
    }

    private static String getLocationNameForRegionId(Location location) {
        String regionName = String.valueOf(location.getX()) + String.valueOf(location.getY()) + String.valueOf(location.getZ()) + location.getWorld().getName();
        regionName = regionName.replace(".", "");
        return regionName;
    }

    public static void removeRegion(Player player, Location location) {
        getWorldGuard().getRegionManager(location.getWorld()).removeRegion(getLocationNameForRegionId(location));
        player.sendMessage(ChatColor.GREEN + Main.pluginInstance.getName() + " - " + ChatColor.GOLD + "You have successfully removed this region");


    }
}
