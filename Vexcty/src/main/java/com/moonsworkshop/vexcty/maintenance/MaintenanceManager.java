package com.moonsworkshop.vexcty.maintenance;

import com.moonsworkshop.vexcty.Vexcty;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class MaintenanceManager {

    private Vexcty main;

    private File file;
    private YamlConfiguration config;

    public MaintenanceManager(Vexcty main) {
        this.main = main;

        if (!main.getDataFolder().exists()) {
            main.getDataFolder().mkdir();
        }

        file = new File(main.getDataFolder(), "servermode.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(file);

    }

    public void setServerMode(MaintenanceType uuid) {
        config.set(uuid.toString(), uuid.name());
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MaintenanceType getMaintenanceMode(UUID uuid) {
        return MaintenanceType.valueOf(config.getString(uuid.toString()));
    }

}