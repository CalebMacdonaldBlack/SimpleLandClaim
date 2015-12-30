package com.gigabytedx.tutorial;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
public class Tutorial extends JavaPlugin {

	public void onEnable() {
		PluginDescriptionFile pdfFile = getDescription();
		Logger logger = getLogger();
		registerEvents();
		logger.info(pdfFile.getName() + " has been enabled (V." + pdfFile.getVersion() + ")");
	}

	public void onDisable() {
		PluginDescriptionFile pdfFile = getDescription();
		Logger logger = getLogger();

		logger.info(pdfFile.getName() + " has been disabled (V." + pdfFile.getVersion() + ")");
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (label.equalsIgnoreCase("getlandblock")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("You must be a player to use this command!");
				return false;
			}
			Player player = (Player) sender;
			player.sendMessage(ChatColor.GREEN + "Giving you a land protection block");
			givePlayerLandProtectionBlock(player);
		}
		return false;
	}
	
	public void registerEvents(){
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PrepareItemCraft(), this);
	}

	private void givePlayerLandProtectionBlock(Player player) {
		ItemStack itemStack = new ItemStack(Material.GOLD_BLOCK, 2);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName(ChatColor.GOLD + "Place this block to claim the land around it");
		List<String> lore = new ArrayList<String>();
		lore.add("craft your own gold blocks to claim more land");
		itemMeta.setLore(lore);
		itemStack.setItemMeta(itemMeta);
		player.getInventory().addItem(itemStack);
	}
}
