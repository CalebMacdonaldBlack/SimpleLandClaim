package com.gigabytedx.simplelandprotect;

import java.util.logging.Logger;

import com.gigabytedx.simplelandprotect.block.GoldBlockProtect;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.gigabytedx.simplelandprotect.block.BlockBreak;
import com.gigabytedx.simplelandprotect.block.BlockPlace;
import com.gigabytedx.simplelandprotect.block.PistonExtend;
import com.gigabytedx.simplelandprotect.commands.GetLandBlock;
import com.gigabytedx.simplelandprotect.interact.EntityInteract;
import com.gigabytedx.simplelandprotect.inventory.PrepareItemCraft;

public class Main extends JavaPlugin implements Listener {
	public static Main pluginInstance;
	
	public void onEnable() {
		PluginDescriptionFile pdfFile = getDescription();
		Logger logger = getLogger();
		registerCommands();
		registerEvents();
		registerConfig();
		logger.info(pdfFile.getName() + " has been enabled (V." + pdfFile.getVersion() + ")");
		pluginInstance = this;
	}

	public void onDisable() {
		PluginDescriptionFile pdfFile = getDescription();
		Logger logger = getLogger();

		logger.info(pdfFile.getName() + " has been disabled (V." + pdfFile.getVersion() + ")");
	}

	private void registerEvents() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this, this);
		pm.registerEvents(new PrepareItemCraft(), this);
		pm.registerEvents(new BlockBreak(), this);
		pm.registerEvents(new PistonExtend(), this);
		pm.registerEvents(new BlockPlace(), this);
		pm.registerEvents(new EntityInteract(), this);
		pm.registerEvents(new GoldBlockProtect(), this);
	}

	private void registerCommands() {
		getCommand("getlandblock").setExecutor(new GetLandBlock());
	}
	
	private void registerConfig(){
		saveDefaultConfig();
	}

	@EventHandler
	private void onNewPlayerJoin(PlayerJoinEvent e) {
		if (!e.getPlayer().hasPlayedBefore()) {
		    new GetLandBlock().givePlayerLandProtectionBlock(e.getPlayer());
		}
	}
}
