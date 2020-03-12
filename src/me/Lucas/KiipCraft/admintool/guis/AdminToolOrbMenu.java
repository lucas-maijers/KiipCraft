/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.admintool.guis;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.storyline.shards.OrbItems;
import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static me.Lucas.KiipCraft.utils.Utils.prefix;
import static org.bukkit.Material.*;

public class AdminToolOrbMenu {

    public static Inventory inv;
    public static String inv_name;
    private static Main plugin;
    private static int row_now = 4;
    public static int inv_rows = row_now * 9;


    public AdminToolOrbMenu(Main plugin) {
        AdminToolOrbMenu.plugin = plugin;
    }

    public static void initialize() {
        inv_name = Utils.chat("&c&lOrbs Menu");

        inv = Bukkit.createInventory(null, inv_rows);
    }

    public static Inventory orbMenu(Player p) {
        Inventory toReturn = Bukkit.createInventory(null, inv_rows, inv_name);

        for (int i = 1; i < 10; i++) {
            Utils.createItem(inv, BLACK_STAINED_GLASS_PANE, 1, i, " ");
        }

        for (int j = 28; j < 31; j++) {
            Utils.createItem(inv, BLACK_STAINED_GLASS_PANE, 1, j, " ");
        }

        for (int k = 33; k < inv_rows + 1; k++) {
            Utils.createItem(inv, BLACK_STAINED_GLASS_PANE, 1, k, " ");
        }

        Utils.createItem(inv, BLACK_STAINED_GLASS_PANE, 1, 10, " ");
        Utils.createItem(inv, BLACK_STAINED_GLASS_PANE, 1, 18, " ");
        Utils.createItem(inv, BLACK_STAINED_GLASS_PANE, 1, 19, " ");
        Utils.createItem(inv, BLACK_STAINED_GLASS_PANE, 1, 27, " ");

        Utils.createItemHead(inv, orb(), 1, 11, "&4&lFire &c&lOrb", "&7Geeft je de Fire Orb!");
        Utils.createItemHead(inv, orb(), 1, 12, "&1&lWater &c&lOrb", "&7Geeft je de Water Orb!");
        Utils.createItemHead(inv, orb(), 1, 13, "&r&lAir &c&lOrb", "&7Geeft je de Air Orb!");
        Utils.createItemHead(inv, orb(), 1, 14, "&6&lEarth &c&lOrb", "&7Geeft je de Earth Orb!");
        Utils.createItemHead(inv, orb(), 1, 15, "&b&lLight&e&lning &c&lOrb", "&7Geeft je de Lightning Orb!");
        Utils.createItemHead(inv, orb(), 1, 16, "&e&lLight &c&lOrb", "&7Geeft je de Light Orb!");
        Utils.createItemHead(inv, orb(), 1, 17, "&5&lDark&0&lness &c&lOrb", "&7Geeft je de Darkness Orb!");
        Utils.createItemHead(inv, orb(), 1, 20, "&a&lLife &c&lOrb", "&7Geeft je de Life Orb!");

        Utils.createItem(inv, ARROW, 1, 31, "Terug");
        Utils.createItem(inv, BARRIER, 1, 32, "&cSluiten");

        toReturn.setContents(inv.getContents());
        return toReturn;
    }

    public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {
        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&4&lFire &c&lOrb"))) {
            p.getInventory().addItem(OrbItems.fireOrb());
            p.sendMessage(prefix + Utils.chat("Je ontvangt de &4&lFire &c&lOrb&7!"));
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&1&lWater &c&lOrb"))) {
            p.getInventory().addItem(OrbItems.waterOrb());
            p.sendMessage(prefix + Utils.chat("Je ontvangt de &1&lWater &c&lOrb&7!"));
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&r&lAir &c&lOrb"))) {
            p.getInventory().addItem(OrbItems.airOrb());
            p.sendMessage(prefix + Utils.chat("Je ontvangt de &f&lAir &c&lOrb&7!"));
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&6&lEarth &c&lOrb"))) {
            p.getInventory().addItem(OrbItems.earthOrb());
            p.sendMessage(prefix + Utils.chat("Je ontvangt de &6&lEarth &c&lOrb&7!"));
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&b&lLight&e&lning &c&lOrb"))) {
            p.getInventory().addItem(OrbItems.lightningOrb());
            p.sendMessage(prefix + Utils.chat("Je ontvangt de &b&lLight&e&lning &c&lOrb&7!"));
        }
        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&e&lLight &c&lOrb"))) {
            p.getInventory().addItem(OrbItems.lightOrb());
            p.sendMessage(prefix + Utils.chat("Je ontvangt de &e&lLight &c&lOrb&7!"));
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&5&lDark&0&lness &c&lOrb"))) {
            p.getInventory().addItem(OrbItems.darknessOrb());
            p.sendMessage(prefix + Utils.chat("Je ontvangt de &5&lDark&0&lness &c&lOrb&7!"));
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&a&lLife &c&lOrb"))) {
            p.getInventory().addItem(OrbItems.lifeOrb());
            p.sendMessage(prefix + Utils.chat("Je ontvangt de &a&lLife &c&lOrb&7!"));
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("Terug"))) {
            p.openInventory(AdminToolGUI.adminToolGUI(p));
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&cSluiten"))) {
            p.closeInventory();
        }
    }

    public static ItemStack orb() {
        ItemStack shard = new ItemStack(Material.ENDER_EYE);
        ItemMeta shardMeta = shard.getItemMeta();

        assert shardMeta != null;
        shardMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);

        shardMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        shard.setItemMeta(shardMeta);
        return shard;
    }
}
