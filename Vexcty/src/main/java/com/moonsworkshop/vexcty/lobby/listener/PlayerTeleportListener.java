package com.moonsworkshop.vexcty.lobby.listener;

import com.moonsworkshop.vexcty.Vexcty;
import com.moonsworkshop.vexcty.lobby.Cosmetics;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class PlayerTeleportListener implements Listener {

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent e) {
        Player p = e.getPlayer();
        if (Cosmetics.balloons.containsKey(p)) {
            if(!Vexcty.multiWorld_mode | Vexcty.lobbyWorlds.contains(p.getWorld())) {
                Bukkit.getScheduler().scheduleSyncDelayedTask(Vexcty.getInstance(), () -> {
                    Cosmetics.balloons.get(p).remove();
                    Cosmetics.balloons.get(p).create();
                }, 2L);
            }
        }
    }

}
