package com.gigabytedx.tutorial;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.gigabytedx.tutorial.block.BlockBreak;
import com.gigabytedx.tutorial.block.BlockPlace;
import com.gigabytedx.tutorial.block.PistonExtend;
import com.gigabytedx.tutorial.commands.GetLandBlock;
import com.gigabytedx.tutorial.interact.EntityInteract;
import com.gigabytedx.tutorial.inventory.PrepareItemCraft;

public class Main extends JavaPlugin {
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
		pm.registerEvents(new PrepareItemCraft(), this);
		pm.registerEvents(new BlockBreak(), this);
		pm.registerEvents(new PistonExtend(), this);
		pm.registerEvents(new BlockPlace(), this);
		pm.registerEvents(new EntityInteract(), this);
	}

	private void registerCommands() {
		getCommand("getlandblock").setExecutor(new GetLandBlock());
	}
	
	private void registerConfig(){
		saveDefaultConfig();
	}
}
