package com.gigabytedx.tutorial.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.gigabytedx.tutorial.Tutorial;
import com.gigabytedx.tutorial.manipulateItems.AddLandProtectionMetaData;

public class GetLandBlock implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("You must be a player to use this command!");
			return false;
		}
		Player player = (Player) sender;
		player.sendMessage(ChatColor.GREEN + Tutorial.pluginInstance.getName() + " - " + ChatColor.GOLD + "Giving you a land protection block");
		givePlayerLandProtectionBlock(player);

		return false;
	}

	private void givePlayerLandProtectionBlock(Player player) {
		ItemStack itemStack = new ItemStack(Material.GOLD_BLOCK, 2);
		itemStack.setItemMeta(AddLandProtectionMetaData.addLandProtectionMeta(itemStack.getItemMeta()));
		player.getInventory().addItem(itemStack);
	}
}
