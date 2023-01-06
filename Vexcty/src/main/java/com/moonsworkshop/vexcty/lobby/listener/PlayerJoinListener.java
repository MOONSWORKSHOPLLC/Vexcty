package com.moonsworkshop.vexcty.lobby.listener;

import com.moonsworkshop.vexcty.Vexcty;
import com.moonsworkshop.vexcty.lobby.Locale;
import com.moonsworkshop.vexcty.lobby.TitleAPI;
import com.moonsworkshop.vexcty.lobby.misc.HiderType;
import com.moonsworkshop.vexcty.lobby.misc.LocationManager;
import com.moonsworkshop.vexcty.util.ItemBuilder;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        e.setJoinMessage(Locale.JOIN_MESSAGE.getMessage(p).replace("%player%", Vexcty.getName(p)));

        double health = Vexcty.cfg.getDouble("player_join.health");
        p.setMaxHealth(health);
        p.setHealth(health);

        p.setFoodLevel(20);

        p.setAllowFlight(false);
        p.setFlying(false);

        p.setFireTicks(0);

        p.setLevel(0);
        p.setExp(0);

        for (PotionEffect effects : p.getActivePotionEffects()) {
            p.removePotionEffect(effects.getType());
        }

        GameMode gameMode;
        String mode = Vexcty.cfg.getString("player_join.gamemode");

        switch (mode) {
            case ("0"):
                gameMode = GameMode.SURVIVAL;
                break;
            case ("1"):
                gameMode = GameMode.CREATIVE;
                break;
            case ("2"):
                gameMode = GameMode.ADVENTURE;
                break;
            case ("3"):
                gameMode = GameMode.SPECTATOR;
                break;
            default:
                gameMode = GameMode.SURVIVAL;
                break;
        }

        p.setGameMode(gameMode);

        if (Vexcty.cfg.getBoolean("player_join.clear_inventory")) {
            p.getInventory().clear();
            p.getInventory().setArmorContents(null);
            p.updateInventory();
        }

        if (Vexcty.cfg.getBoolean("title.enabled")) {
            if (Vexcty.placeholderApi) {
                TitleAPI.sendTitle(p, 20, 40, 20, Vexcty.getPlaceholderString(p, "title.title").replace("%player%", Vexcty.getName(p)), Vexcty.getPlaceholderString(p, "title.subtitle").replace("%player%", Vexcty.getName(p)));
            } else {
                TitleAPI.sendTitle(p, 20, 40, 20, Vexcty.getString("title.title").replace("%player%", Vexcty.getName(p)), Vexcty.getString("title.subtitle").replace("%player%", Vexcty.getName(p)));
            }
        }


        if (Vexcty.cfg.getBoolean("tablist_header_footer.enabled")) {
            if (Vexcty.cfg.getBoolean("tablist_header_footer.update_tablist")) {
                Bukkit.getScheduler().scheduleSyncRepeatingTask(Vexcty.getInstance(), () -> {
                    if (Vexcty.placeholderApi) {
                        TitleAPI.sendTabTitle(p, Vexcty.getPlaceholderString(p, "tablist_header_footer.header"),
                                Vexcty.getPlaceholderString(p, "tablist_header_footer.footer"));
                    } else {
                        TitleAPI.sendTabTitle(p, Vexcty.getString("tablist_header_footer.header"),
                                Vexcty.getString("tablist_header_footer.footer"));
                    }
                }, 0L, 20L);
            } else {
                if (Vexcty.placeholderApi) {
                    TitleAPI.sendTabTitle(p, Vexcty.getPlaceholderString(p, "tablist_header_footer.header"),
                            Vexcty.getPlaceholderString(p, "tablist_header_footer.footer"));
                } else {
                    TitleAPI.sendTabTitle(p, Vexcty.getString("tablist_header_footer.header"),
                            Vexcty.getString("tablist_header_footer.footer"));
                }
            }


        }


        ItemBuilder teleporter = new ItemBuilder(Vexcty.getMaterial("hotbar_items.teleporter.material"), 1,
                (short) Vexcty.cfg.getInt("hotbar_items.teleporter.subid")).setDisplayName(
                ChatColor.translateAlternateColorCodes('&', Vexcty.cfg.getString("hotbar_items.teleporter.displayname")))
                .setLore(Vexcty.cfg.getStringList("hotbar_items.teleporter.lore"));

        if (Vexcty.cfg.getBoolean("hotbar_items.teleporter.enabled")) {
            p.getInventory().setItem(Vexcty.cfg.getInt("hotbar_items.teleporter.slot"), teleporter);
        }

        ItemBuilder hider = new ItemBuilder(Vexcty.getMaterial("hotbar_items.player_hider.show_all.material"),
                1, (short) Vexcty.cfg.getInt("hotbar_items.player_hider.show_all.subid"))
                .setDisplayName(ChatColor.translateAlternateColorCodes('&',
                        Vexcty.cfg.getString("hotbar_items.player_hider.show_all.displayname")))
                .setLore(Vexcty.cfg.getStringList("hotbar_items.player_hider.show_all.lore"));

        if (Vexcty.cfg.getBoolean("hotbar_items.player_hider.enabled")) {
            p.getInventory().setItem(Vexcty.cfg.getInt("hotbar_items.player_hider.slot"), hider);
        }

        ItemBuilder cosmetics = new ItemBuilder(Vexcty.getMaterial("hotbar_items.cosmetics.material"), 1,
                (short) Vexcty.cfg.getInt("hotbar_items.cosmetics.subid")).setDisplayName(
                ChatColor.translateAlternateColorCodes('&', Vexcty.cfg.getString("hotbar_items.cosmetics.displayname")))
                .setLore(Vexcty.cfg.getStringList("hotbar_items.cosmetics.lore"));

        if (Vexcty.cfg.getBoolean("hotbar_items.cosmetics.enabled")) {
            p.getInventory().setItem(Vexcty.cfg.getInt("hotbar_items.cosmetics.slot"), cosmetics);
        }

        ItemBuilder no_gadget = new ItemBuilder(Vexcty.getMaterial("hotbar_items.gadget.unequipped.material"), 1,
                (short) Vexcty.cfg.getInt("hotbar_items.gadget.unequipped.subid")).setDisplayName(
                ChatColor.translateAlternateColorCodes('&', Vexcty.cfg.getString("hotbar_items.gadget.unequipped.displayname")))
                .setLore(Vexcty.cfg.getStringList("hotbar_items.gadget.unequipped.lore"));

        if (Vexcty.cfg.getBoolean("hotbar_items.gadget.enabled")) {
            p.getInventory().setItem(Vexcty.cfg.getInt("hotbar_items.gadget.slot"), no_gadget);
        }

        ItemBuilder silentlobby = new ItemBuilder(
                Vexcty.getMaterial("hotbar_items.silentlobby.deactivated.material"), 1,
                (short) Vexcty.cfg.getInt("hotbar_items.silentlobby.deactivated.subid"))
                .setDisplayName(ChatColor.translateAlternateColorCodes('&',
                        Vexcty.cfg.getString("hotbar_items.silentlobby.deactivated.displayname")))
                .setLore(Vexcty.cfg.getStringList("hotbar_items.silentlobby.deactivated.lore"));

        if (Vexcty.cfg.getBoolean("hotbar_items.silentlobby.enabled") && p.hasPermission("advancedlobby.silentlobby")) {
            p.getInventory().setItem(Vexcty.cfg.getInt("hotbar_items.silentlobby.slot"), silentlobby);
        }

        ItemBuilder shield = new ItemBuilder(
                Vexcty.getMaterial("hotbar_items.shield.deactivated.material"), 1,
                (short) Vexcty.cfg.getInt("hotbar_items.shield.deactivated.subid"))
                .setDisplayName(ChatColor.translateAlternateColorCodes('&',
                        Vexcty.cfg.getString("hotbar_items.shield.deactivated.displayname")))
                .setLore(Vexcty.cfg.getStringList("hotbar_items.shield.deactivated.lore"));

        if (Vexcty.cfg.getBoolean("hotbar_items.shield.enabled") && p.hasPermission("advancedlobby.shield")) {
            p.getInventory().setItem(Vexcty.cfg.getInt("hotbar_items.shield.slot"), shield);
        }

        ItemBuilder custom_item = new ItemBuilder(
                Vexcty.getMaterial("hotbar_items.custom_item.material"), 1,
                (short) Vexcty.cfg.getInt("hotbar_items.custom_item.subid"))
                .setDisplayName(ChatColor.translateAlternateColorCodes('&',
                        Vexcty.cfg.getString("hotbar_items.custom_item.displayname")))
                .setLore(Vexcty.cfg.getStringList("hotbar_items.custom_item.lore"));

        if (Vexcty.cfg.getBoolean("hotbar_items.custom_item.enabled") && p.hasPermission("advancedlobby.custom_item")) {
            p.getInventory().setItem(Vexcty.cfg.getInt("hotbar_items.custom_item.slot"), custom_item);
        }

        for (Player players : Vexcty.playerHider.keySet()) {
            if (Vexcty.playerHider.get(players) == HiderType.VIP) {
                if (!p.hasPermission("advancedlobby.player_hider.bypass")) {
                    players.hidePlayer(p);
                }
            }
            if (Vexcty.playerHider.get(players) == HiderType.NONE) {
                players.hidePlayer(p);
            }
        }

        for (Player players : Vexcty.silentLobby) {
            players.hidePlayer(p);
            p.hidePlayer(players);
        }

        if (!p.hasPlayedBefore()) {
            Location location = LocationManager.getLocation(Vexcty.cfg.getString("spawn_location"));
            if (location != null) {
                p.teleport(location);
            }
        }

        if (Vexcty.cfg.getBoolean("player_join.join_at_spawn")) {
            Location location = LocationManager.getLocation(Vexcty.cfg.getString("spawn_location"));
            if (location != null) {
                p.teleport(location);
            }
        }


    }

}
