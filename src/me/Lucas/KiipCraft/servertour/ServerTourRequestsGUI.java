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
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;

import static org.bukkit.Bukkit.createInventory;
import static org.bukkit.Material.*;

public class ServerTourRequestsGUI {

    public static Inventory inv;
    public static String inv_name;
    public static String warpSettingName;
    private static Main plugin;
    private static ConfigManager cfgm = ConfigManager.getManager();
    private static int row_now = 4;
    public static int inv_rows = row_now * 9;

    private static FileConfiguration warps;
    private static File warpsfile;

    public ServerTourRequestsGUI(Main plugin) {
        ServerTourRequestsGUI.plugin = plugin;
    }

    public static void initialize() {
        inv_name = Utils.chat("&6&lServertour Warps");

        warpsfile = new File(plugin.getDataFolder(), "warps.yml");
        warps = YamlConfiguration.loadConfiguration(warpsfile);

        inv = Bukkit.createInventory(null, inv_rows);
    }

    public static Inventory serverTourUI(Player p) {
        Inventory toReturn = createInventory(null, inv_rows, inv_name);
        int l = 1;

        for (int j = 28; j < 32; j++) {
            Utils.createItem(inv, BLACK_STAINED_GLASS_PANE, 1, j, " ");
        }

        for (int k = 33; k < inv_rows + 1; k++) {
            Utils.createItem(inv, BLACK_STAINED_GLASS_PANE, 1, k, " ");
        }

        Utils.createItem(inv, BARRIER, 1, 32, "&cSluiten");

        for (String key : warps.getConfigurationSection("Warps").getKeys(false)) {
            ConfigurationSection cs = cfgm.getWarpsCFG().getConfigurationSection("Warps." + key);
            Utils.createItemLore(inv, DIRT, 1, l, key, "&7Door: &6&l" + cs.getString("creator"), "&7X: &6" + cs.getDouble("X"), "&7Y: &6" + cs.getDouble("Y"), "&7Z: &6" + cs.getDouble("Z"), "&7World: &6" + cs.getString("world"), " ", "Klik om instellingen voor deze servertour locatie op te vragen");
            l++;
        }

        toReturn.setContents(inv.getContents());
        return toReturn;
    }

    public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {

        for (String key : warps.getConfigurationSection("Warps").getKeys(false)) {
            if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat(key))) {
                warpSettingName = key;
                ServerTourRequestSettings.initialize();
                p.openInventory(ServerTourRequestSettings.requestSettings(p));
            }
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&cSluiten"))) {
            p.closeInventory();
        }
    }
}
