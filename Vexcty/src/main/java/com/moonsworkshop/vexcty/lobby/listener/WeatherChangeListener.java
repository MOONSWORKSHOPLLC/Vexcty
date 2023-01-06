package com.moonsworkshop.vexcty.lobby.listener;

import com.moonsworkshop.vexcty.Vexcty;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherChangeListener implements Listener {

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e) {
        if (!Vexcty.multiWorld_mode | Vexcty.lobbyWorlds.contains( e.getWorld())) {
            if(Vexcty.cfg.getBoolean("weather.lock_weather")) {
                e.setCancelled(true);
            }
        }
    }

}
