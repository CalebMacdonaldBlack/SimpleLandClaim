package com.gigabytedx.simplelandprotect.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.gigabytedx.simplelandprotect.Main;
import com.gigabytedx.simplelandprotect.manipulateItems.AddLandProtectionMetaData;

public class GetLandBlock implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("You must be a player to use this command!");
			return false;
		}
		
		Player player = (Player) sender;
		if(player.hasPermission("simplelandprotection.getlandblock")){
			player.sendMessage(ChatColor.GREEN + Main.pluginInstance.getName() + " - " + ChatColor.GOLD + "Giving you a land protection block");
			givePlayerLandProtectionBlock(player);
		}else{
			player.sendMessage(ChatColor.RED + "you do not have permission to perform this command!");
		}
		return false;
	}

	private void givePlayerLandProtectionBlock(Player player) {
		ItemStack itemStack = new ItemStack(Material.GOLD_BLOCK, 2);
		itemStack.setItemMeta(AddLandProtectionMetaData.addLandProtectionMeta(itemStack.getItemMeta()));
		player.getInventory().addItem(itemStack);
	}
}
