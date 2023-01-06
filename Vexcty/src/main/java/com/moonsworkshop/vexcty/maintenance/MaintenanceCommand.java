package com.moonsworkshop.vexcty.maintenance;

import com.moonsworkshop.vexcty.Vexcty;
import com.moonsworkshop.vexcty.util.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MaintenanceCommand implements CommandExecutor {

    private Vexcty vexcty;

    public MaintenanceCommand(Vexcty vexcty) {
        this.vexcty = vexcty;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        Player player = (Player) commandSender;

        if (player.hasPermission("f2erg.vexcty.admin")) {
            if (args.length == 0) {
                if (args[0].equalsIgnoreCase("offline")) {
                    vexcty.getMaintenanceManager().setServerMode(MaintenanceType.OFFLINE);
                } else if (args[0].equalsIgnoreCase("online")) {
                    vexcty.getMaintenanceManager().setServerMode(MaintenanceType.ONLINE);
                }
            } else {
                player.sendMessage(CC.RED + "Invalid Usage! Please use /setmaintenance <online|offline>");
            }
        } else {
            player.sendMessage(CC.RED + "You do not have permission to use this command.");
        }

        return false;
    }
}