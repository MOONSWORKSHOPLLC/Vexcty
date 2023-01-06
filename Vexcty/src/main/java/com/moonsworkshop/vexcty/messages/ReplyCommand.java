package com.moonsworkshop.vexcty.messages;

import com.moonsworkshop.vexcty.Vexcty;
import com.moonsworkshop.vexcty.util.CC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ReplyCommand implements CommandExecutor {

    private Vexcty main;

    public ReplyCommand(Vexcty main) {
        this.main = main;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;


            if (args.length >= 1) {
                if (main.getRecentMessages().containsKey(player.getUniqueId())) {
                    UUID uuid = main.getRecentMessages().get(player.getUniqueId());
                    if (Bukkit.getPlayer(uuid) != null) {
                        Player target = Bukkit.getPlayer(uuid);

                        StringBuilder builder = new StringBuilder();
                        for (int i = 0; i < args.length; i++) {
                            builder.append(args[i]).append(" ");
                        }

                        if (target == player) {
                            player.sendMessage(ChatColor.RED + "You cannot message yourself");
                        } else {
                            target.sendMessage(ChatColor.GRAY + main.getRankManager().getRank(target.getUniqueId()).getDisplay() + " " + target.getName() + CC.YELLOW + " »» " + CC.GRAY + "You: " + builder);
                            // From [OWNER] epicprogamer975 »» You: hi
                            player.sendMessage(CC.GRAY + "You »» " + main.getRankManager().getRank(target.getUniqueId()).getDisplay() + " " + target.getName() +  ": " + builder.toString());
                            // You »» [ADMIN] 2bak: u ded
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "The player you messaged has gone offline!");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "No one messaged you!");
                }

            } else {
                player.sendMessage(ChatColor.RED + "Invalid Usage! Please use /r <message>");
            }
        }


        return false;
    }

}