package com.moonsworkshop.vexcty.lobby.listener;

import com.moonsworkshop.vexcty.Vexcty;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class EntityExplodeListener implements Listener {

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent e) {
        if (!Vexcty.multiWorld_mode | Vexcty.lobbyWorlds.contains(e.getEntity().getWorld())) {
            e.setCancelled(true);
            e.blockList().clear();
        }
    }

}
