/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.managers;

import me.Lucas.KiipCraft.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class ConfigManager {

    private static ConfigManager manager = new ConfigManager();
    public File warpsfile;
    public File syncChestFile;
    public File dungeonGatesFile;
    public File eventsFile;
    public FileConfiguration syncChestCFG;
    public FileConfiguration eventsCFG;
    public FileConfiguration warpsCFG;
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
        syncChestFile = new File(plugin.getDataFolder(), "syncchests.yml");
        dungeonGatesFile = new File(plugin.getDataFolder(), "dungeons.yml");
        eventsFile = new File(plugin.getDataFolder(), "events.yml");

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

        if (!syncChestFile.exists()) {
            try {
                syncChestFile.createNewFile();
            } catch (IOException e) {
                Bukkit.getServer().getLogger().log(Level.WARNING, "File could not be created.");
            }
        }

        if (!eventsFile.exists()) {
            try {
                eventsFile.createNewFile();
            } catch (IOException e) {
                Bukkit.getServer().getLogger().log(Level.WARNING, "File could not be created.");
            }
        }

        warpsCFG = YamlConfiguration.loadConfiguration(warpsfile);
        syncChestCFG = YamlConfiguration.loadConfiguration(syncChestFile);
        dungeonGatesCFG = YamlConfiguration.loadConfiguration(dungeonGatesFile);
        eventsCFG = YamlConfiguration.loadConfiguration(eventsFile);

    }

    /* Get Gedeelte */
    public FileConfiguration getWarpsCFG() {
        warpsfile = new File(plugin.getDataFolder(), "warps.yml");
        warpsCFG = YamlConfiguration.loadConfiguration(warpsfile);
        return warpsCFG;
    }

    public FileConfiguration getEventsCFG() {
        eventsFile = new File(plugin.getDataFolder(), "events.yml");
        eventsCFG = YamlConfiguration.loadConfiguration(eventsFile);
        return eventsCFG;
    }

    public FileConfiguration getDungeonGatesCFG() {
        dungeonGatesFile = new File(plugin.getDataFolder(), "dungeons.yml");
        dungeonGatesCFG = YamlConfiguration.loadConfiguration(dungeonGatesFile);
        return dungeonGatesCFG;
    }

    public FileConfiguration getSyncChestsCFG() {
        syncChestFile = new File(plugin.getDataFolder(), "syncchests.yml");
        syncChestCFG = YamlConfiguration.loadConfiguration(syncChestFile);
        return syncChestCFG;
    }

    /* Oplaan Gedeelte */
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

    public void saveEvents() {
        try {
            eventsFile = new File(plugin.getDataFolder(), "events.yml");
            eventsCFG = YamlConfiguration.loadConfiguration(eventsFile);
            if (!eventsCFG.isConfigurationSection("Events")) {
                eventsCFG.createSection("Events");
            }
            eventsCFG.save(eventsFile);
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

    public void saveSyncChests() {
        try {
            syncChestFile = new File(plugin.getDataFolder(), "syncchests.yml");
            syncChestCFG = YamlConfiguration.loadConfiguration(syncChestFile);
            if (!syncChestCFG.isConfigurationSection("SyncedChests")) {
                syncChestCFG.createSection("SyncedChests");
            }
            syncChestCFG.save(syncChestFile);
        } catch (IOException e) {
            Bukkit.getServer().getLogger().log(Level.WARNING, "File could not be saved.");
        }
    }

    /* Reload Gedeelte */
    public void reloadDungeonGates() {
        dungeonGatesCFG = YamlConfiguration.loadConfiguration(dungeonGatesFile);
    }

    public void reloadEvents() {
        eventsCFG = YamlConfiguration.loadConfiguration(eventsFile);
    }

    public void reloadWarps() {
        warpsCFG = YamlConfiguration.loadConfiguration(warpsfile);
    }

    public void reloadSyncChests() {
        syncChestCFG = YamlConfiguration.loadConfiguration(syncChestFile);
    }

}
