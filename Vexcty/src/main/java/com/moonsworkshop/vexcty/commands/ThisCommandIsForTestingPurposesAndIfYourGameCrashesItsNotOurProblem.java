package com.moonsworkshop.vexcty.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ThisCommandIsForTestingPurposesAndIfYourGameCrashesItsNotOurProblem implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        return false;
    }
}
