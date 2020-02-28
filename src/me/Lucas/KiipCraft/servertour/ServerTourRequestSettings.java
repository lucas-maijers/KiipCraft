/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.servertour;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.managers.ConfigManager;
import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;

import static org.bukkit.Material.*;

public class ServerTourRequestSettings {

    public static Inventory inv;
    public static String inv_name;
    private static Main plugin;
    private static ConfigManager cfgm = ConfigManager.getManager();
    private static int row_now = 3;
    public static int inv_rows = row_now * 9;

    private static FileConfiguration warps;
    private static File warpsfile;

    private static String warpName;

    public ServerTourRequestSettings(Main plugin) {
        ServerTourRequestSettings.plugin = plugin;
    }

    public static void initialize() {
        inv_name = Utils.chat("&6&lServertour Warp Settings");

        warpsfile = new File(plugin.getDataFolder(), "warps.yml");
        warps = YamlConfiguration.loadConfiguration(warpsfile);

        warpName = ServerTourRequestsGUI.warpSettingName;

        inv = Bukkit.createInventory(null, inv_rows);
    }

    public static Inventory requestSettings(Player p) {
        Inventory toReturn = Bukkit.createInventory(null, inv_rows, inv_name);

        for (int i = 1; i < 10; i++) {
            Utils.createItem(inv, BLACK_STAINED_GLASS_PANE, 1, i, " ");
        }

        for (int j = 19; j < 22 + 1; j++) {
            Utils.createItem(inv, BLACK_STAINED_GLASS_PANE, 1, j, " ");
        }
        for (int k = 24; k < inv_rows + 1; k++) {
            Utils.createItem(inv, BLACK_STAINED_GLASS_PANE, 1, k, " ");
        }
        Utils.createItem(inv, BLACK_STAINED_GLASS_PANE, 1, 10, " ");
        Utils.createItem(inv, BLACK_STAINED_GLASS_PANE, 1, 18, " ");

        Utils.createItem(inv, COMPASS, 1, 11, "&6&lTELEPORT");

        Utils.createItem(inv, RED_CONCRETE, 1, 12, "&c&lREMOVE");

        Utils.createItem(inv, BARRIER, 1, 23, "&cSluiten");

        toReturn.setContents(inv.getContents());
        return toReturn;
    }


    public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&6&lTELEPORT"))) {
            ConfigurationSection cs = warps.getConfigurationSection("Warps." + warpName);

            World w = p.getWorld();

            assert cs != null;
            p.teleport(new Location(w, cs.getDouble("X"), cs.getDouble("Y"), cs.getDouble("Z")));
            p.sendMessage(Utils.prefix + "Je teleporteert naar de locatie van de Warp van: §c" + warpName + "§7!");
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&c&lREMOVE"))) {
            ConfigurationSection cs = warps.getConfigurationSection("Warps");
            p.sendMessage(Utils.prefix + "Je hebt de warp van: §c" + warpName + " §7verwijderd!");
            try {
                assert cs != null;
                cs.set(warpName, null);
                warps.save(warpsfile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            p.closeInventory();
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&cSluiten"))) {
            p.closeInventory();
        }
    }
}
