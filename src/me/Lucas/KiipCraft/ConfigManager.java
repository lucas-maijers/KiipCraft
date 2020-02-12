/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class ConfigManager {

    private static ConfigManager manager = new ConfigManager();
    public FileConfiguration warpscfg;
    public File warpsfile;
    private Main plugin = Main.getPlugin(Main.class);

    public static ConfigManager getManager() {
        return manager;
    }

    public void setup() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }

        warpsfile = new File(plugin.getDataFolder(), "warps.yml");

        if (!warpsfile.exists()) {
            try {
                warpsfile.createNewFile();
            } catch (IOException e) {
                Bukkit.getServer().getLogger().log(Level.WARNING, "File could not be created.");
            }
        }

        warpscfg = YamlConfiguration.loadConfiguration(warpsfile);

    }

    public FileConfiguration getWarpscfg() {
        warpsfile = new File(plugin.getDataFolder(), "warps.yml");
        warpscfg = YamlConfiguration.loadConfiguration(warpsfile);
        return warpscfg;
    }

    public void saveWarps() {
        try {
            warpsfile = new File(plugin.getDataFolder(), "warps.yml");
            warpscfg = YamlConfiguration.loadConfiguration(warpsfile);
            if (!warpscfg.isConfigurationSection("Warps")) {
                warpscfg.createSection("Warps");
            }
            warpscfg.save(warpsfile);
        } catch (IOException e) {
            Bukkit.getServer().getLogger().log(Level.WARNING, "File could not be saved.");
        }
    }

    public void reloadWarps() {
        warpscfg = YamlConfiguration.loadConfiguration(warpsfile);
    }

}
