package com.moonsworkshop.vexcty.lobby.listener;

import com.moonsworkshop.vexcty.Vexcty;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.LeavesDecayEvent;

public class LeavesDecayListener implements Listener {

    @EventHandler
    public void onLeavesDecay(LeavesDecayEvent e) {
        if (!Vexcty.multiWorld_mode | Vexcty.lobbyWorlds.contains(e.getBlock().getWorld())) {
            e.setCancelled(true);
        }
    }

}
