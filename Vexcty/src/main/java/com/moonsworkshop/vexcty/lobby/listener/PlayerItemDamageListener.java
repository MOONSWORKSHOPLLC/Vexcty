package com.moonsworkshop.vexcty.lobby.listener;

import com.moonsworkshop.vexcty.Vexcty;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;

public class PlayerItemDamageListener implements Listener {

    @EventHandler
    public void onPlayerItemDamage(PlayerItemDamageEvent e) {
        if (e.getItem().getType() == Material.FISHING_ROD && e.getItem().hasItemMeta() && ChatColor.stripColor(e.getItem().getItemMeta().getDisplayName()).equals(ChatColor.stripColor(Vexcty.getString("hotbar_items.gadget.equipped.displayname").replace("%gadget%", Vexcty.getString("inventories.cosmetics_gadgets.grappling_hook_gadget.displayname"))))) {
            e.setCancelled(true);
        }
    }

}