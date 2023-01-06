package com.moonsworkshop.vexcty.lobby.listener;

import com.moonsworkshop.vexcty.Vexcty;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class PlayerInteractEntityListener implements Listener {

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {
        Player p = e.getPlayer();
        if (!Vexcty.multiWorld_mode | Vexcty.lobbyWorlds.contains(p.getWorld())) {
            if (e.getRightClicked().getType() == EntityType.ITEM_FRAME && !Vexcty.build.contains(p)) {
                e.setCancelled(true);
            }
            if (p.getItemInHand().getType() == Material.NAME_TAG && !Vexcty.build.contains(p)) {
                e.setCancelled(true);
            }
        }
    }

}
