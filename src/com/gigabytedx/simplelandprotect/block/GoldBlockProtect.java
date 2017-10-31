package com.gigabytedx.simplelandprotect.block;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

import java.util.stream.Collectors;

public class GoldBlockProtect implements Listener {

    @EventHandler
    public void onPistonExtendEvent(BlockPistonExtendEvent e) {
        if (e.getBlocks().stream().filter(block -> block.getType().equals(Material.GOLD_BLOCK)).collect(Collectors.toList()).size() > 0) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPistonRetractEvent(BlockPistonRetractEvent e) {
        if (e.getBlocks().stream().filter(block -> block.getType().equals(Material.GOLD_BLOCK)).collect(Collectors.toList()).size() > 0) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent e) {
        e.blockList().removeIf(b -> b.getType() == Material.GOLD_BLOCK);
    }
}
