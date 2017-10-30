package com.gigabytedx.simplelandprotect.block;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;

public class PistonExtend implements Listener{

	@EventHandler
	public void onPistonExtend(BlockPistonExtendEvent event){
		for(Block block: event.getBlocks()){
			if(block.getType().equals(Material.GOLD_BLOCK))
				event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPistonRetract(BlockPistonRetractEvent event){
		for(Block block: event.getBlocks()){
			if(block.getType().equals(Material.GOLD_BLOCK))
				event.setCancelled(true);
		}
	}
}
