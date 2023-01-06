package com.moonsworkshop.vexcty.lobby.listener;

import com.moonsworkshop.vexcty.Vexcty;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.util.Vector;

import java.lang.reflect.Field;

public class PlayerFishListener implements Listener {


    @EventHandler
    public void onPlayerFish(PlayerFishEvent e) {
        Player p = e.getPlayer();
        Entity hook = null;

        try {
            Field hookEntity = PlayerFishEvent.class.getDeclaredField("hookEntity");
            hookEntity.setAccessible(true);
            hook = (Entity) hookEntity.get(e);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        String gadgetName = ChatColor.stripColor(Vexcty.getString("hotbar_items.gadget.equipped.displayname").replaceAll("%gadget%", Vexcty.getString("inventories.cosmetics_gadgets.grappling_hook_gadget.displayname")));
        if (p.getItemInHand().hasItemMeta() && ChatColor.stripColor(p.getItemInHand().getItemMeta().getDisplayName()).equals(gadgetName)) {
            //if(p.getItemInHand().hasItemMeta() && p.getItemInHand().getItemMeta().getDisplayName().equals("§bGrappling hook §8× §7rightclick")) {
            if (!Vexcty.multiWorld_mode | Vexcty.lobbyWorlds.contains(p.getWorld())) {
                if (!Vexcty.build.contains(p)) {
                    if (hook.getLocation().subtract(0, 1, 0).getBlock().getType() != Material.AIR) {
                        Vector pV = p.getLocation().toVector();
                        Vector hV = hook.getLocation().toVector();
                        Vector v = hV.clone().subtract(pV).normalize().multiply(1.5D).setY(0.5D);

                        p.setVelocity(v);
                        Vexcty.playSound(p, p.getLocation(), "gadgets.grappling_hook");

                        for (Player players : Bukkit.getOnlinePlayers()) {
                            if (p != players) {
                                if (!Vexcty.silentLobby.contains(p) && !Vexcty.silentLobby.contains(players) && !Vexcty.playerHider.containsKey(players)) {
                                    Vexcty.playSound(players, p.getLocation(), "gadgets.grappling_hook");
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
