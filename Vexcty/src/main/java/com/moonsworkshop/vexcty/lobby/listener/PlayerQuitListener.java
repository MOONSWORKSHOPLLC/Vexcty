package com.moonsworkshop.vexcty.lobby.listener;

import com.moonsworkshop.vexcty.Vexcty;
import com.moonsworkshop.vexcty.lobby.Cosmetics;
import com.moonsworkshop.vexcty.lobby.Locale;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        e.setQuitMessage(Locale.QUIT_MESSAGE.getMessage(p).replace("%player%", Vexcty.getName(p)));

        if (Cosmetics.balloons.containsKey(p)) {
            Cosmetics.balloons.get(p).remove();
        }

        Vexcty.build.remove(p);
        Vexcty.buildInventory.remove(p);
        Vexcty.fly.remove(p);
        Vexcty.playerHider.remove(p);
        Vexcty.shield.remove(p);
        Vexcty.silentLobby.remove(p);
    }

}
