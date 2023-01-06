package com.moonsworkshop.vexcty.util;

import com.moonsworkshop.vexcty.Vexcty;
import org.bukkit.inventory.ItemStack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class ItemDatabase {

    private static class SimpleItemData {
        int id;
        short metadata;

        SimpleItemData(int id, short metadata) {
            this.id = id;
            this.metadata = metadata;
        }
    }

    private static Map<String, SimpleItemData> itemAliases = new HashMap<>();

    private ItemDatabase() {}

    public static void init() {
        InputStream itemsFileStream = Vexcty.instancee.getResource("items.csv");

        if(itemsFileStream == null) {
            Vexcty.instancee.getLogger().log(Level.SEVERE, "Failed to load items file.");
            return;
        }

        try(BufferedReader itemReader = new BufferedReader(new InputStreamReader(itemsFileStream))) {
            String line;

            while((line = itemReader.readLine()) != null) {
                if(line.startsWith("#")) continue;

                String[] split = line.split(",");

                itemAliases.put(split[0], new SimpleItemData(Integer.parseInt(split[1]), Short.parseShort(split[2])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static ItemStack getItemByAlias(String alias) {
        SimpleItemData itemData = itemAliases.get(alias);

        if(itemData == null) return null;

        return new ItemStack(itemData.id, 1, itemData.metadata);
    }

}
