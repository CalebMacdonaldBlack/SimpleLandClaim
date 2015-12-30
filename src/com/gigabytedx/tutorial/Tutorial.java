package com.gigabytedx.tutorial;

import java.util.logging.Logger;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.gigabytedx.tutorial.block.BlockBreak;
import com.gigabytedx.tutorial.block.PistonExtend;
import com.gigabytedx.tutorial.commands.GetLandBlock;
import com.gigabytedx.tutorial.inventory.PrepareItemCraft;

public class Tutorial extends JavaPlugin {

	public void onEnable() {
		PluginDescriptionFile pdfFile = getDescription();
		Logger logger = getLogger();
		registerCommands();
		registerEvents();
		logger.info(pdfFile.getName() + " has been enabled (V." + pdfFile.getVersion() + ")");
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
	}

	private void registerCommands() {
		getCommand("getlandblock").setExecutor(new GetLandBlock());
	}

}
