package com.moonsworkshop.vexcty.rank;

import com.moonsworkshop.vexcty.Vexcty;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Perks implements Listener {


    private Cache<UUID, Long> cooldown = CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.SECONDS).build();

    private static Vexcty vexcty;

    public Perks(Vexcty vexcty) {
        this.vexcty = vexcty;
    }

    Player player = null;

    PlayerRank rank = vexcty.getRankManager().getRank(player.getUniqueId());

    public static void perks() {

        Player player = null;

        for (PlayerRank rank : PlayerRank.values()) {
            if (vexcty.getRankManager().getRank(player.getUniqueId()) == PlayerRank.OWNER ||
                    vexcty.getRankManager().getRank(player.getUniqueId()) == PlayerRank.ADMIN ||
                    vexcty.getRankManager().getRank(player.getUniqueId()) == PlayerRank.MOD ||
                    vexcty.getRankManager().getRank(player.getUniqueId()) == PlayerRank.BUILDER ||
                    vexcty.getRankManager().getRank(player.getUniqueId()) == PlayerRank.GOD ||
                    vexcty.getRankManager().getRank(player.getUniqueId()) == PlayerRank.LEGEND ||
                    vexcty.getRankManager().getRank(player.getUniqueId()) == PlayerRank.DONOR) {
                player.setFlying(true);
                player.getEnderChest().clear();

            } else {
                player.setFlying(false);
                player.getEnderChest().clear();
            }



        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {


        if (vexcty.getRankManager().getRank(player.getUniqueId()) == PlayerRank.MEMBER) {

            if (!cooldown.asMap().containsKey(player.getUniqueId())) {
                e.setCancelled(false); // dont cancel the async event and send the message
                cooldown.put(player.getUniqueId(), System.currentTimeMillis() + 3000); // must wait 3 seconds
            } else {
                long distance = cooldown.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                if (System.currentTimeMillis() > 1000) { // see if the wait time is less than 1 second
                    e.setCancelled(true);
                    player.sendMessage(ChatColor.RED + "You must wait " + TimeUnit.MILLISECONDS.toSeconds(distance) + " second to type again. Ranked player can bypass this.");
                } else {
                    e.setCancelled(true);
                    player.sendMessage(ChatColor.RED + "You must wait " + TimeUnit.MILLISECONDS.toSeconds(distance) + " seconds to type again. Ranked player can bypass this.");
                }

            }

        }

    }
}