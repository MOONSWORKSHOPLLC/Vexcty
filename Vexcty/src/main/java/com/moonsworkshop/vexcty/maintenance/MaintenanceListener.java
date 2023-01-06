package com.moonsworkshop.vexcty.maintenance;

import com.moonsworkshop.vexcty.Vexcty;
import com.moonsworkshop.vexcty.lang.Lang;
import com.moonsworkshop.vexcty.rank.PlayerRank;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class MaintenanceListener implements Listener {

    private Vexcty vexcty;

    public MaintenanceListener(Vexcty vexcty) {
        this.vexcty = vexcty;
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        Player player = e.getPlayer();

        if (vexcty.getMaintenanceManager().getMaintenanceMode(player.getUniqueId()) == MaintenanceType.OFFLINE) { // if the server is offline
            if (vexcty.getRankManager().getRank(player.getUniqueId()) == PlayerRank.MEMBER ||
                    vexcty.getRankManager().getRank(player.getUniqueId()) == PlayerRank.DONOR ||
                    vexcty.getRankManager().getRank(player.getUniqueId()) == PlayerRank.LEGEND ||
                    vexcty.getRankManager().getRank(player.getUniqueId()) == PlayerRank.GOD) { // if the player is a regular player using ranks
                if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.EN)) {
                    player.kickPlayer(ChatColor.RED + "Server is under maintenance.");
                } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.AR)) {
                    player.kickPlayer(ChatColor.RED + "المخدم قيد الصيانة.");
                } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.FR)) {
                    player.kickPlayer(ChatColor.RED + "Le serveur est en maintenance.");
                } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.IT)) {
                    player.kickPlayer(ChatColor.RED + "Il server è in manutenzione.");
                } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.JA)) {
                    player.kickPlayer(ChatColor.RED + "サーバーはメンテナンス中です。");
                } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.LA)) {
                    player.kickPlayer(ChatColor.RED + "Servo in tutela est.");
                } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.PA)) {
                    player.kickPlayer(ChatColor.RED + "ਸਰਵਰ ਸੰਭਾਲ ਅਧੀਨ ਹੈ।");
                } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.RU)) {
                    player.kickPlayer(ChatColor.RED + "Сервер находится на обслуживании.");
                } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.ES)) {
                    player.kickPlayer(ChatColor.RED + "El servidor está en mantenimiento.");
                } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.TR)) {
                    player.kickPlayer(ChatColor.RED + "Sunucu bakımda.");
                } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.UR)) {
                    player.kickPlayer(ChatColor.RED + "سرور کی دیکھ بھال جاری ہے۔");
                }
            } else {
                return;
            }
        }
    }

}