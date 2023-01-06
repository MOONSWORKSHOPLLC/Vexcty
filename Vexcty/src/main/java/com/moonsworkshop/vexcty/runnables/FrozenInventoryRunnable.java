package com.moonsworkshop.vexcty.runnables;

import com.moonsworkshop.vexcty.Vexcty;
import com.moonsworkshop.vexcty.managers.inventories.FrozenInventory;
import com.moonsworkshop.vexcty.player.PlayerData;
import com.moonsworkshop.vexcty.util.CC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class FrozenInventoryRunnable implements Runnable {

    private Vexcty plugin;

    public FrozenInventoryRunnable() {
        this.plugin = Vexcty.getInstance();
    }

    @Override
    public void run() {

        plugin.getServer().getOnlinePlayers().forEach(players -> {

            PlayerData datas = plugin.getPlayerManager().getPlayerData(players);
            Player player = Bukkit.getPlayer(datas.getPlayerID());

            switch(datas.getPlayerState()) {

                case FROZEN:
                    if(player.getOpenInventory() == null || !player.getOpenInventory().getTitle().equals(CC.RED + "You are frozen!")) {
                        player.openInventory(FrozenInventory.getFrozenInventory());
                    }
                    break;

                default:
                    break;
            }

        });

    }

}