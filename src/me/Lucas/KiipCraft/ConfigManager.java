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
    public FileConfiguration warpsCFG;
    public File warpsfile;
    public File dungeonGatesFile;
    public FileConfiguration dungeonGatesCFG;
    private Main plugin = Main.getPlugin(Main.class);

    public static ConfigManager getManager() {
        return manager;
    }

    public void setup() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }

        warpsfile = new File(plugin.getDataFolder(), "warps.yml");
        dungeonGatesFile = new File(plugin.getDataFolder(), "dungeons.yml");

        if (!warpsfile.exists()) {
            try {
                warpsfile.createNewFile();
            } catch (IOException e) {
                Bukkit.getServer().getLogger().log(Level.WARNING, "File could not be created.");
            }
        }

        if (!dungeonGatesFile.exists()) {
            try {
                dungeonGatesFile.createNewFile();
            } catch (IOException e) {
                Bukkit.getServer().getLogger().log(Level.WARNING, "File could not be created.");
            }
        }

        warpsCFG = YamlConfiguration.loadConfiguration(warpsfile);
        dungeonGatesCFG = YamlConfiguration.loadConfiguration(dungeonGatesFile);

    }

    public FileConfiguration getWarpsCFG() {
        warpsfile = new File(plugin.getDataFolder(), "warps.yml");
        warpsCFG = YamlConfiguration.loadConfiguration(warpsfile);
        return warpsCFG;
    }

    public FileConfiguration getDungeonGatesCFG() {
        dungeonGatesFile = new File(plugin.getDataFolder(), "dungeons.yml");
        dungeonGatesCFG = YamlConfiguration.loadConfiguration(dungeonGatesFile);
        return dungeonGatesCFG;
    }

    public void saveDungeonGates() {
        try {
            dungeonGatesFile = new File(plugin.getDataFolder(), "dungeons.yml");
            dungeonGatesCFG = YamlConfiguration.loadConfiguration(dungeonGatesFile);
            if (!dungeonGatesCFG.isConfigurationSection("Dungeons")) {
                dungeonGatesCFG.createSection("Dungeons");
            }
            dungeonGatesCFG.save(dungeonGatesFile);
        } catch (IOException e) {
            Bukkit.getServer().getLogger().log(Level.WARNING, "File could not be saved.");
        }
    }

    public void saveWarps() {
        try {
            warpsfile = new File(plugin.getDataFolder(), "warps.yml");
            warpsCFG = YamlConfiguration.loadConfiguration(warpsfile);
            if (!warpsCFG.isConfigurationSection("Warps")) {
                warpsCFG.createSection("Warps");
            }
            warpsCFG.save(warpsfile);
        } catch (IOException e) {
            Bukkit.getServer().getLogger().log(Level.WARNING, "File could not be saved.");
        }
    }

    public void reloadDungeonGates() {
        dungeonGatesCFG = YamlConfiguration.loadConfiguration(dungeonGatesFile);
    }

    public void reloadWarps() {
        warpsCFG = YamlConfiguration.loadConfiguration(warpsfile);
    }

}
