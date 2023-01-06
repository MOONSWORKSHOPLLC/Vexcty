package com.moonsworkshop.vexcty.lobby.listener;

import com.moonsworkshop.vexcty.Vexcty;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerUnleashEntityEvent;

public class PlayerUnleashEntityListener implements Listener {

    @EventHandler
    public void onPlayerUnleashEntity(PlayerUnleashEntityEvent e) {
        Player p = e.getPlayer();
        if (!Vexcty.multiWorld_mode | Vexcty.lobbyWorlds.contains(p.getWorld())) {
            if (!Vexcty.build.contains(p)) {
                e.setCancelled(true);
            }
        }
    }

}
