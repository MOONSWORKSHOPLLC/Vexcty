package com.moonsworkshop.vexcty.lang;

import com.moonsworkshop.vexcty.Vexcty;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class LangManager {

    private File file;
    private YamlConfiguration config;
    private Vexcty vexcty;

    public LangManager(Vexcty vexcty) {
        this.vexcty = vexcty;

        if (!vexcty.getDataFolder().exists()) {
            vexcty.getDataFolder().mkdir();
        }

        file = new File(vexcty.getDataFolder(), "languages.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public void setLang(UUID uuid, Lang lang) {

        config.set(uuid.toString(), lang.name());
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //    public boolean getLang(UUID uuid, Lang lang) { return Lang.valueOf(config.getString(uuid.toString())); }
    public boolean getLang(UUID uuid, Lang lang) {
        Player player = null;
//        return Lang.valueOf(config.getString(uuid.toString()));
        return vexcty.getLangManager().getLang(player.getUniqueId(), lang); // get the lang from the player
    }

}
