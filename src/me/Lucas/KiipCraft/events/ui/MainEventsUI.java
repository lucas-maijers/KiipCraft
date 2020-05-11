/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.events.ui;

import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class MainEventsUI {

    public static Inventory inv;
    public static String inventory_name;
    public static int inv_rows = 4 * 9;
    public static int singleinvrow = 10;

    public static void initialize() {
        inventory_name = Utils.chat("&3&lEvents GUI");

        inv = Bukkit.createInventory(null, inv_rows);
    }

    public static Inventory mainGUI(Player p) {
        Inventory toReturn = Bukkit.createInventory(null, inv_rows, inventory_name);

        // Glass
        for (int i = 1; i < singleinvrow; i++) {
            Utils.createItem(inv, Material.BLACK_STAINED_GLASS_PANE, 1, i, " ");
        }

        for (int j = 28; j < 32; j++) {
            Utils.createItem(inv, Material.BLACK_STAINED_GLASS_PANE, 1, j, " ");
        }

        for (int k = 33; k < inv_rows + 1; k++) {
            Utils.createItem(inv, Material.BLACK_STAINED_GLASS_PANE, 1, k, " ");
        }

        Utils.createItem(inv, Material.BLACK_STAINED_GLASS_PANE, 1, 10, " ");
        Utils.createItem(inv, Material.BLACK_STAINED_GLASS_PANE, 1, 18, " ");
        Utils.createItem(inv, Material.BLACK_STAINED_GLASS_PANE, 1, 19, " ");
        Utils.createItem(inv, Material.BLACK_STAINED_GLASS_PANE, 1, 27, " ");


        Utils.createItemLore(inv, Material.CRAFTING_TABLE, 1, 11, "&6&lBuild Battle", "&7Build Battle Controls!");

        Utils.createItemLore(inv, Material.DIAMOND_ORE, 1, 12, "&6&lGeluks Gravers", "&7Geluks Gravers Controls!");

        Utils.createItemLore(inv, Material.DIAMOND_SHOVEL, 1, 13, "&6&lSpleef", "&7Spleef Controls!");

        Utils.createItemLore(inv, Material.DIAMOND_SWORD, 1, 14, "&6&lTriathlon", "&7Triathlon Controls!");


        // Close GUI
        Utils.createItem(inv, Material.BARRIER, 1, 32, "&cSluiten");

        toReturn.setContents(inv.getContents());
        return toReturn;
    }

    public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {


        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&6&lGeluks Gravers"))) {
            p.sendMessage(Utils.prefix + Utils.chat("Je opent de &c&lGeluks Gravers Controls&7!"));
            p.openInventory(GeluksGraversUI.geluksGraversGUI(p));
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&6&lTriathlon"))) {
            p.sendMessage(Utils.prefix + Utils.chat("Je opent de &c&lTriathlon Controls&7!"));
            p.openInventory(TriathlonUI.triathlonGUI(p));
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&6&lSpleef"))) {
            p.sendMessage(Utils.prefix + Utils.chat("Je opent de &c&lSpleef Controls&7!"));
            p.openInventory((SpleefUI.spleefGUI(p)));
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&6&lBuild Battle"))) {
            p.sendMessage(Utils.prefix + Utils.chat("Je opent de &cBuild Battle Controls&7!"));
            p.openInventory(BuildBattleUI.buildBattleGUI(p));
        }


        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&cSluiten"))) {
            p.closeInventory();
        }
    }
}
