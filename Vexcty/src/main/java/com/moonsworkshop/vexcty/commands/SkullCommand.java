package com.moonsworkshop.vexcty.commands;

import com.moonsworkshop.vexcty.api.heads.SkullCreator;
import com.moonsworkshop.vexcty.util.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SkullCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("f2erg.vexcty.admin")) {
                if (args.length == 0) {
                    Player target = (Player) Bukkit.getOfflinePlayer(args[0]);
                    ItemStack playerSkull = SkullCreator.itemFromUuid(target.getUniqueId());
                    playerSkull.getItemMeta().setDisplayName(target.getDisplayName() + "'s head");
                } else {
                    player.sendMessage(CC.RED + "Invalid Usage! Please use /skull <player>");
                }
            } else {
                player.sendMessage(CC.RED + "You do not have permission to use this command.");
            }
        }

        return false;
    }
}
