package com.moonsworkshop.vexcty.lobby.listener;

import com.moonsworkshop.vexcty.Vexcty;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerListPingListener implements Listener {

    @EventHandler
    public void onServerListPing(ServerListPingEvent e) {
        if (Vexcty.cfg.getBoolean("motd.enabled")) {
            String s = Vexcty.cfg.getString("motd.first_line") + "\n" + Vexcty.cfg.getString("motd.second_line");
            e.setMotd(ChatColor.translateAlternateColorCodes('&', s));
        }
    }

}
