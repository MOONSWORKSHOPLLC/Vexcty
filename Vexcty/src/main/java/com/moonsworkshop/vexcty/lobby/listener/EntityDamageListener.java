package com.moonsworkshop.vexcty.lobby.listener;

import com.moonsworkshop.vexcty.Vexcty;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageListener implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (!Vexcty.multiWorld_mode | Vexcty.lobbyWorlds.contains(p.getWorld())) {
                e.setCancelled(true);
            }
        } else if (Vexcty.cfg.getBoolean("disable_mob_damage")) {
            if (!Vexcty.multiWorld_mode | Vexcty.lobbyWorlds.contains(e.getEntity().getWorld())) {
                e.setCancelled(true);
            }
        }

    }

}
