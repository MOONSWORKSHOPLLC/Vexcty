package com.moonsworkshop.vexcty.lobby.listener;

import com.moonsworkshop.vexcty.Vexcty;
import com.moonsworkshop.vexcty.lobby.Locale;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChatListener implements Listener {

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String message = e.getMessage().replace("%", "%%");

        for (Player silentPlayers : Vexcty.silentLobby) {
            e.getRecipients().remove(silentPlayers);
        }

        if (Vexcty.silentLobby.contains(p)) {
            e.setCancelled(true);
            p.sendMessage(Locale.SILENTLOBBY_CHAT_BLOCKED.getMessage(p));
        }

        if (Vexcty.globalMute) {
            if (!p.hasPermission("advancedlobby.globalmute.bypass")) {
                e.setCancelled(true);
                p.sendMessage(Locale.GLOBALMUTE_CHAT_BLOCKED.getMessage(p));
            }
        }

        if (Vexcty.cfg.getBoolean("chat_format.enabled")) {
            if (p.hasPermission("advancedlobby.chatcolor")) {
                message = ChatColor.translateAlternateColorCodes('&', message);
            }
            e.setFormat(Vexcty.getString("chat_format.format").replace("%player%", Vexcty.getName(p)).replace("%message%", message));
        }

    }

}
