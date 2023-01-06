package com.moonsworkshop.vexcty.rank;

import org.bukkit.ChatColor;

public enum PlayerRank {


    OWNER(ChatColor.DARK_RED + "[OWNER]"),
    ADMIN(ChatColor.RED + "[ADMIN]"),
    MOD(ChatColor.GREEN + "[MODERATOR]"),
    DEVELOPER(ChatColor.BLACK + "[DEV]"),
    BUILDER(ChatColor.DARK_GREEN + "[BUILDER]"),
    YOUTUBE(ChatColor.RED + "[YOUTUBE]"),
    GOD(ChatColor.BLUE + "[GOD]"),
    LEGEND(ChatColor.AQUA + "[LEGEND]"),
    DONOR(ChatColor.BLUE + "[DONOR]"),
    MEMBER(ChatColor.YELLOW + "[MEMBER]");

    private String display;

    PlayerRank(String display) {
        this.display = display;
    }
    public String getDisplay() { return display; }

}