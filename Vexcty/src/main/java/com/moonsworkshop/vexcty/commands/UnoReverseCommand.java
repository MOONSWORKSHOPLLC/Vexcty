package com.moonsworkshop.vexcty.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnoReverseCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            player.sendMessage("⣰⣾⣿⣿⣿⠿⠿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣆");
            player.sendMessage("⣿⣿⣿⡿⠋  ⣿⣿⣿⣿⣿⣿⣿⣿⠿⠛⠋⣉⣉⣉⡉⠙⠻⣿⣿");
            player.sendMessage("⣿⣿⣿⣇  ⣿⣿⣿⣿⣿⡿⠛⢉⣤⣶⣾⣿⣿⣿⣿⣿⣿⣦⡀⠹");
            player.sendMessage("⣿⣿  ⢠⣾⣿⣿⣿⠟⢁⣠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡄");
            player.sendMessage("⣿⣿⣿⣿⣿⣿⣿⠟⢁⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷");
            player.sendMessage("⣿⣿⣿⣿⣿⡟⠁⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
            player.sendMessage("⣿⣿⣿⣿⠋⢠⣾⣿⣿⣿⣿⣿⣿⡿⠿⠿⠿⠿⣿⣿⣿⣿⣿⣿⣿⣿");
            player.sendMessage("⣿⣿⡿⠁⣰⣿⣿⣿⣿⣿⣿⣿⣿⠗    ⣿⣿⣿⣿⣿⣿⣿⡟");
            player.sendMessage("⣿⡿⠁⣼⣿⣿⣿⣿⣿⣿⡿⠋   ⣠⣄⢰⣿⣿⣿⣿⣿⣿⣿⠃");
            player.sendMessage("⡿⠁⣼⣿⣿⣿⣿⣿⣿⣿⡇  ⡴⠚⢿⣿⣿⣿⣿⣿⣿⣿⣿⡏⢠");
            player.sendMessage("⠃⢰⣿⣿⣿⣿⣿⣿⡿⣿⣿⠴⠋  ⢸⣿⣿⣿⣿⣿⣿⣿⡟⢀⣾");
            player.sendMessage("⢀⣿⣿⣿⣿⣿⣿⣿⠃    ⢀⣴⣿⣿⣿⣿⣿⣿⣿⡟⢀⣾⣿");
            player.sendMessage("⣿⣿⣿⣿⣿⣿⣿    ⢶⣿⣿⣿⣿⣿⣿⣿⣿⠏⢀⣾⣿⣿");
            player.sendMessage("⣿⣿⣿⣿⣿⣿⣿⣷⣶⣶⣶⣶⣶⣿⣿⣿⣿⣿⣿⣿⠋⣠⣿⣿⣿⣿");
            player.sendMessage("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⢁⣼⣿⣿⣿⣿⣿");
            player.sendMessage("⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⢁⣴⣿⣿⣿⣿⣿⣿⣿");
            player.sendMessage("⠈⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠟⢁⣴⣿⣿⣿⣿⠗  ⣿⣿");
            player.sendMessage("⣆⠈⠻⢿⣿⣿⣿⣿⣿⣿⠿⠛⣉⣤⣾⣿⣿⣿⣿⣿⣇ ⠺⣷⣿⣿");
            player.sendMessage("⣿⣿⣦⣄⣈⣉⣉⣉⣡⣤⣶⣿⣿⣿⣿⣿⣿⣿⣿  ⣀⣼⣿⣿⣿");
            player.sendMessage("⠻⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣶⣾⣿⣿⡿⠟");

        }

        return false;
    }
}




