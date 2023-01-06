package com.moonsworkshop.vexcty.lobby.listener;

import com.moonsworkshop.vexcty.Vexcty;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;

public class HangingBreakByEntityListener implements Listener {

    @EventHandler
    public void onHangingBreakByEntity(HangingBreakByEntityEvent e) {
        if (e.getRemover() instanceof Player) {
            Player p = (Player) e.getRemover();
            if (!Vexcty.multiWorld_mode | Vexcty.lobbyWorlds.contains(p.getWorld())) {
                e.setCancelled(true);
            }
        }
    }

}
